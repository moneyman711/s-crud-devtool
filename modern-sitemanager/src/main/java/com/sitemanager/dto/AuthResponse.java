package com.sitemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Authentication Response DTO - JWT token response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String accessToken;
    
    private String refreshToken;
    
    private String tokenType;
    
    private Long expiresIn;
    
    private String username;
    
    private String email;
    
    private List<String> roles;
    
    @Builder.Default
    private String message = "Authentication successful";
}
