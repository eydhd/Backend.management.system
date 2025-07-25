package com.example.end.service;

import com.example.end.dto.LoginRequest;
import com.example.end.dto.UserDto;
import com.example.end.entity.User;
import com.example.end.repository.UserRepository;
import com.example.end.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public UserDto register(User user) {
        // 检查用户名是否已存在
        if (userRepository.existsByName(user.getName())) {
            throw new IllegalArgumentException("用户名已存在");
        }
        
        // 检查手机号是否已存在
        if (userRepository.existsByMobilePhoneNumber(user.getMobilePhoneNumber())) {
            throw new IllegalArgumentException("手机号已存在");
        }
        
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    
    public String login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByName(loginRequest.getUsername());
        
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("用户不存在");
        }
        
        User user = userOptional.get();
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("密码错误");
        }
        
        return jwtUtil.generateToken(user.getName());
    }
    
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        return convertToDto(user);
    }
    
    public UserDto updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        
        // 检查新用户名是否已存在（排除当前用户）
        if (!user.getName().equals(userDetails.getName()) && 
            userRepository.existsByName(userDetails.getName())) {
            throw new IllegalArgumentException("用户名已存在");
        }
        
        // 检查新手机号是否已存在（排除当前用户）
        if (!user.getMobilePhoneNumber().equals(userDetails.getMobilePhoneNumber()) && 
            userRepository.existsByMobilePhoneNumber(userDetails.getMobilePhoneNumber())) {
            throw new IllegalArgumentException("手机号已存在");
        }
        
        user.setName(userDetails.getName());
        user.setMobilePhoneNumber(userDetails.getMobilePhoneNumber());
        
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        
        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }
    
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("用户不存在");
        }
        userRepository.deleteById(id);
    }
    
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setCreationTime(user.getCreationTime());
        dto.setMobilePhoneNumber(user.getMobilePhoneNumber());
        dto.setUserType(user.getUserType().name());
        return dto;
    }
}