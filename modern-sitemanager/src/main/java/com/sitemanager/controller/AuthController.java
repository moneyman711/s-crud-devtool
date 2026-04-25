package com.sitemanager.controller;

import com.sitemanager.dto.AuthResponse;
import com.sitemanager.dto.LoginRequest;
import com.sitemanager.dto.RefreshTokenRequest;
import com.sitemanager.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Authentication Controller - REST API for user authentication
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "User authentication and authorization APIs")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user and generate JWT tokens")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        log.info("Login request received for identifier: {}", request.getIdentifier());
        
        AuthResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token", description = "Generate new access token using refresh token")
    public ResponseEntity<AuthResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {
        log.info("Token refresh request received");
        
        AuthResponse response = authenticationService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @Operation(summary = "User logout", description = "Logout current user and invalidate session")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("Logout request received");
        
        authenticationService.logout();
        
        // Clear security context
        new SecurityContextLogoutHandler().logout(request, response, null);
        
        return ResponseEntity.ok().body("{\"message\": \"Logout successful\"}");
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Get authenticated user details")
    public ResponseEntity<?> getCurrentUser() {
        try {
            var currentUser = authenticationService.getCurrentUser();
            
            return ResponseEntity.ok().body(java.util.Map.of(
                "username", currentUser.getUsername(),
                "email", currentUser.getEmail(),
                "fullName", currentUser.getFullName(),
                "roles", currentUser.getAuthorities()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("{\"error\": \"Not authenticated\"}");
        }
    }
}
