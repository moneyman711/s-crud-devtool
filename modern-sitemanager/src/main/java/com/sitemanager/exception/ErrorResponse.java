package com.sitemanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Global Error Response DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    
    private Integer status;
    
    private String error;
    
    private String message;
    
    private String path;
    
    private LocalDateTime timestamp;
}
