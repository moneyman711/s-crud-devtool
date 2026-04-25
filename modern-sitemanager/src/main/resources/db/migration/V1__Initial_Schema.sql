-- V1__Initial_Schema.sql
-- Initial database schema for Modern SiteManager

-- Create sitemanager table
CREATE TABLE IF NOT EXISTS `sitemanager` (
    `AdminID` INT PRIMARY KEY,
    `FirstName` VARCHAR(100) NOT NULL,
    `MiddleName` VARCHAR(100),
    `LastName` VARCHAR(100),
    `Gender` VARCHAR(10),
    `BirthDate` DATE,
    `MaritalStatus` VARCHAR(20),
    `EmpCode` VARCHAR(50) UNIQUE,
    `JoiningDate` DATE,
    `LeavingDate` DATE,
    `Address` VARCHAR(500),
    `City` VARCHAR(100),
    `State` VARCHAR(100),
    `PIN` VARCHAR(20),
    `Landline` VARCHAR(20),
    `Mobile` VARCHAR(20),
    `Email` VARCHAR(255) UNIQUE,
    `Username` VARCHAR(100) NOT NULL UNIQUE,
    `Password` VARCHAR(255) NOT NULL,
    `PasswordType` SMALLINT NOT NULL DEFAULT 1 COMMENT '1=BCrypt, 2=SHA256',
    `AccessModule` VARCHAR(1000),
    `SuperAdminRight` SMALLINT NOT NULL DEFAULT 0,
    `LoginRole` VARCHAR(50) NOT NULL DEFAULT 'USER',
    `CurrentStatus` SMALLINT NOT NULL DEFAULT 1 COMMENT '1=Active, 0=Inactive',
    `LoginStatus` SMALLINT NOT NULL DEFAULT 1 COMMENT '1=Enabled, 0=Disabled',
    `MultiLogin` SMALLINT NOT NULL DEFAULT 0 COMMENT '1=Allowed, 0=Not Allowed',
    `MenuType` VARCHAR(50),
    `UpdateDateTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `CreatedDateTime` DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (`Username`),
    INDEX idx_email (`Email`),
    INDEX idx_empcode (`EmpCode`),
    INDEX idx_status (`CurrentStatus`),
    INDEX idx_role (`LoginRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create sitemanager_authorization table
CREATE TABLE IF NOT EXISTS `sitemanager_authorization` (
    `AuthorizationID` INT AUTO_INCREMENT PRIMARY KEY,
    `AdminID` INT NOT NULL,
    `ModuleName` VARCHAR(100) NOT NULL,
    `Authority` VARCHAR(100) NOT NULL,
    `Description` VARCHAR(255),
    `IsActive` BOOLEAN NOT NULL DEFAULT TRUE,
    `CreatedDateTime` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `CreatedBy` INT,
    `ExpiryDate` DATETIME,
    FOREIGN KEY (`AdminID`) REFERENCES `sitemanager`(`AdminID`) ON DELETE CASCADE,
    INDEX idx_admin_id (`AdminID`),
    INDEX idx_module (`ModuleName`),
    INDEX idx_active (`IsActive`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create sitemanager_photograph table
CREATE TABLE IF NOT EXISTS `sitemanager_photograph` (
    `PhotoID` INT AUTO_INCREMENT PRIMARY KEY,
    `AdminID` INT NOT NULL,
    `FileName` VARCHAR(255) NOT NULL,
    `FilePath` VARCHAR(500) NOT NULL,
    `FileSize` BIGINT,
    `ContentType` VARCHAR(100),
    `IsPrimary` BOOLEAN DEFAULT FALSE,
    `Description` VARCHAR(255),
    `UploadedDateTime` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `UploadedBy` INT,
    FOREIGN KEY (`AdminID`) REFERENCES `sitemanager`(`AdminID`) ON DELETE CASCADE,
    INDEX idx_admin_id (`AdminID`),
    INDEX idx_primary (`IsPrimary`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create audit_log table for security auditing
CREATE TABLE IF NOT EXISTS `audit_log` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT,
    `username` VARCHAR(100),
    `action` VARCHAR(50) NOT NULL,
    `entity_type` VARCHAR(100),
    `entity_id` VARCHAR(50),
    `old_value` TEXT,
    `new_value` TEXT,
    `ip_address` VARCHAR(45),
    `user_agent` VARCHAR(255),
    `timestamp` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `status` VARCHAR(20) DEFAULT 'SUCCESS',
    INDEX idx_user (`user_id`),
    INDEX idx_action (`action`),
    INDEX idx_timestamp (`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create failed_login_attempts table for brute force protection
CREATE TABLE IF NOT EXISTS `failed_login_attempts` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(100) NOT NULL,
    `ip_address` VARCHAR(45) NOT NULL,
    `attempt_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (`username`),
    INDEX idx_ip (`ip_address`),
    INDEX idx_time (`attempt_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
