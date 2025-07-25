package com.example.end.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3-20个字符之间")
    @Column(unique = true, nullable = false)
    private String name;
    
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能少于6个字符")
    @Column(nullable = false)
    private String password;
    
    @CreatedDate
    @Column(name = "creation_time", nullable = false, updatable = false)
    private LocalDateTime creationTime;
    
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Column(name = "mobile_phone_number", unique = true, nullable = false)
    private String mobilePhoneNumber;
    
    // 用户类型：普通用户、俱乐部、管理员、公司
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType = UserType.ORDINARY;
    
    public enum UserType {
        ORDINARY,    // 普通用户
        CLUB,        // 俱乐部
        ADMIN,       // 管理员
        FIRM         // 公司
    }
    
    public User() {}
    
    public User(String name, String password, String mobilePhoneNumber, UserType userType) {
        this.name = name;
        this.password = password;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.userType = userType;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
    
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
    
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }
    
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }
    
    public UserType getUserType() {
        return userType;
    }
    
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}