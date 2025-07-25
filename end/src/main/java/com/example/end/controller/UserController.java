package com.example.end.controller;

import com.example.end.common.ApiResponse;
import com.example.end.dto.LoginRequest;
import com.example.end.dto.UserDto;
import com.example.end.entity.User;
import com.example.end.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "普通用户相关接口")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "注册新用户")
    public ResponseEntity<ApiResponse<UserDto>> register(@Valid @RequestBody User user) {
        user.setUserType(User.UserType.ORDINARY);
        UserDto userDto = userService.register(user);
        return ResponseEntity.ok(ApiResponse.success("注册成功", userDto));
    }
    
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录获取token")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        return ResponseEntity.ok(ApiResponse.success("登录成功", token));
    }
    
    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "用户登出系统")
    public ResponseEntity<ApiResponse<String>> logout() {
        // 在实际应用中，这里应该处理token的失效逻辑
        return ResponseEntity.ok(ApiResponse.success("登出成功"));
    }
    
    @PostMapping("/log-off")
    @Operation(summary = "注销账户", description = "永久注销用户账户")
    public ResponseEntity<ApiResponse<String>> logOff(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(ApiResponse.success("账户注销成功"));
    }
    
    @GetMapping("/club/list-info")
    @Operation(summary = "获取俱乐部列表", description = "获取所有俱乐部信息")
    public ResponseEntity<ApiResponse<List<UserDto>>> getClubList() {
        // 这里应该返回俱乐部类型的用户列表
        List<UserDto> users = userService.getAllUsers().stream()
                .filter(user -> "CLUB".equals(user.getUserType()))
                .toList();
        return ResponseEntity.ok(ApiResponse.success(users));
    }
    
    @PostMapping("/club/new")
    @Operation(summary = "创建俱乐部", description = "创建新的俱乐部账户")
    public ResponseEntity<ApiResponse<UserDto>> createClub(@Valid @RequestBody User club) {
        club.setUserType(User.UserType.CLUB);
        UserDto userDto = userService.register(club);
        return ResponseEntity.ok(ApiResponse.success("俱乐部创建成功", userDto));
    }
    
    @PutMapping("/club/update")
    @Operation(summary = "更新俱乐部", description = "更新俱乐部信息")
    public ResponseEntity<ApiResponse<UserDto>> updateClub(
            @RequestParam Long id, 
            @Valid @RequestBody User clubDetails) {
        UserDto userDto = userService.updateUser(id, clubDetails);
        return ResponseEntity.ok(ApiResponse.success("俱乐部更新成功", userDto));
    }
    
    @DeleteMapping("/club/Delete")
    @Operation(summary = "删除俱乐部", description = "删除俱乐部账户")
    public ResponseEntity<ApiResponse<String>> deleteClub(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("俱乐部删除成功"));
    }
    
    @GetMapping("/ordinary/list-info")
    @Operation(summary = "获取普通用户列表", description = "获取所有普通用户信息（管理员权限）")
    public ResponseEntity<ApiResponse<List<UserDto>>> getOrdinaryUserList() {
        List<UserDto> users = userService.getAllUsers().stream()
                .filter(user -> "ORDINARY".equals(user.getUserType()))
                .toList();
        return ResponseEntity.ok(ApiResponse.success(users));
    }
    
    @PostMapping("/ordinary/new")
    @Operation(summary = "创建普通用户", description = "创建新的普通用户（管理员权限）")
    public ResponseEntity<ApiResponse<UserDto>> createOrdinaryUser(@Valid @RequestBody User user) {
        user.setUserType(User.UserType.ORDINARY);
        UserDto userDto = userService.register(user);
        return ResponseEntity.ok(ApiResponse.success("普通用户创建成功", userDto));
    }
    
    @PutMapping("/ordinary/update")
    @Operation(summary = "更新普通用户", description = "更新普通用户信息（管理员权限）")
    public ResponseEntity<ApiResponse<UserDto>> updateOrdinaryUser(
            @RequestParam Long id, 
            @Valid @RequestBody User userDetails) {
        UserDto userDto = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(ApiResponse.success("普通用户更新成功", userDto));
    }
    
    @DeleteMapping("/ordinary/Delete")
    @Operation(summary = "删除普通用户", description = "删除普通用户账户（管理员权限）")
    public ResponseEntity<ApiResponse<String>> deleteOrdinaryUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("普通用户删除成功"));
    }
    
    @GetMapping("/administrator/list-info")
    @Operation(summary = "获取管理员列表", description = "获取所有管理员信息（超级管理员权限）")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAdministratorList() {
        List<UserDto> users = userService.getAllUsers().stream()
                .filter(user -> "ADMIN".equals(user.getUserType()))
                .toList();
        return ResponseEntity.ok(ApiResponse.success(users));
    }
    
    @PostMapping("/administrator/new")
    @Operation(summary = "创建管理员", description = "创建新的管理员账户（超级管理员权限）")
    public ResponseEntity<ApiResponse<UserDto>> createAdministrator(@Valid @RequestBody User admin) {
        admin.setUserType(User.UserType.ADMIN);
        UserDto userDto = userService.register(admin);
        return ResponseEntity.ok(ApiResponse.success("管理员创建成功", userDto));
    }
    
    @PutMapping("/administrator/update")
    @Operation(summary = "更新管理员", description = "更新管理员信息（超级管理员权限）")
    public ResponseEntity<ApiResponse<UserDto>> updateAdministrator(
            @RequestParam Long id, 
            @Valid @RequestBody User adminDetails) {
        UserDto userDto = userService.updateUser(id, adminDetails);
        return ResponseEntity.ok(ApiResponse.success("管理员更新成功", userDto));
    }
    
    @GetMapping("/firm/list-info")
    @Operation(summary = "获取公司列表", description = "获取所有公司信息（管理员权限）")
    public ResponseEntity<ApiResponse<List<UserDto>>> getFirmList() {
        List<UserDto> users = userService.getAllUsers().stream()
                .filter(user -> "FIRM".equals(user.getUserType()))
                .toList();
        return ResponseEntity.ok(ApiResponse.success(users));
    }
    
    @PostMapping("/firm/new")
    @Operation(summary = "创建公司", description = "创建新的公司账户（管理员权限）")
    public ResponseEntity<ApiResponse<UserDto>> createFirm(@Valid @RequestBody User firm) {
        firm.setUserType(User.UserType.FIRM);
        UserDto userDto = userService.register(firm);
        return ResponseEntity.ok(ApiResponse.success("公司创建成功", userDto));
    }
    
    @PutMapping("/firm/update")
    @Operation(summary = "更新公司", description = "更新公司信息（管理员权限）")
    public ResponseEntity<ApiResponse<UserDto>> updateFirm(
            @RequestParam Long id, 
            @Valid @RequestBody User firmDetails) {
        UserDto userDto = userService.updateUser(id, firmDetails);
        return ResponseEntity.ok(ApiResponse.success("公司更新成功", userDto));
    }
    
    @DeleteMapping("/firm/Delete")
    @Operation(summary = "删除公司", description = "删除公司账户（管理员权限）")
    public ResponseEntity<ApiResponse<String>> deleteFirm(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("公司删除成功"));
    }
}