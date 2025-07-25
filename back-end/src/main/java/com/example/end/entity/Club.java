package com.example.end.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "clubs")
@EntityListeners(AuditingEntityListener.class)
public class Club {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "俱乐部名称不能为空")
    @Size(min = 2, max = 50, message = "俱乐部名称长度必须在2-50个字符之间")
    @Column(unique = true, nullable = false)
    private String name;
    
    @CreatedDate
    @Column(name = "creation_time", nullable = false, updatable = false)
    private LocalDateTime creationTime;
    
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Column(name = "mobile_phone_number", unique = true, nullable = false)
    private String mobilePhoneNumber;
    
    public Club() {}
    
    public Club(String name, String mobilePhoneNumber) {
        this.name = name;
        this.mobilePhoneNumber = mobilePhoneNumber;
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
}