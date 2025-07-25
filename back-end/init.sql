-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS asd_firm;

-- 使用数据库
USE asd_firm;

-- 创建用户表
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    creation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    mobile_phone_number VARCHAR(11) NOT NULL UNIQUE,
    user_type VARCHAR(20) NOT NULL DEFAULT 'ORDINARY',
    INDEX idx_name (name),
    INDEX idx_mobile (mobile_phone_number),
    INDEX idx_user_type (user_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建俱乐部表
CREATE TABLE clubs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    creation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    mobile_phone_number VARCHAR(11) NOT NULL UNIQUE,
    INDEX idx_name (name),
    INDEX idx_mobile (mobile_phone_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建管理员表
CREATE TABLE admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    creation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    mobile_phone_number VARCHAR(11) NOT NULL UNIQUE,
    INDEX idx_name (name),
    INDEX idx_mobile (mobile_phone_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建公司表
CREATE TABLE firms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    creation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    mobile_phone_number VARCHAR(11) NOT NULL UNIQUE,
    INDEX idx_name (name),
    INDEX idx_mobile (mobile_phone_number)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
