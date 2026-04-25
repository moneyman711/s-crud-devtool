package com.sitemanager.service;

import com.sitemanager.dto.AuthResponse;
import com.sitemanager.dto.LoginRequest;
import com.sitemanager.dto.RefreshTokenRequest;
import com.sitemanager.entity.Sitemanager;
import com.sitemanager.repository.SitemanagerRepository;
import com.sitemanager.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Authentication Service - Handles user authentication and token management
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SitemanagerRepository sitemanagerRepository;

    /**
     * Authenticate user and generate JWT tokens
     */
    @Transactional(readOnly = true)
    public AuthResponse authenticate(LoginRequest request) {
        log.info("Authentication attempt for identifier: {}", request.getIdentifier());

        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getIdentifier(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Load user details
            Sitemanager sitemanager = sitemanagerRepository.findByUsername(request.getIdentifier())
                    .orElseThrow(() -> new BadCredentialsException("User not found"));

            // Generate tokens
            String accessToken = jwtTokenProvider.generateToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            // Extract roles
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            log.info("Authentication successful for user: {}", request.getIdentifier());

            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .tokenType("Bearer")
                    .expiresIn(86400000L) // 24 hours
                    .username(sitemanager.getUsername())
                    .email(sitemanager.getEmail())
                    .roles(roles)
                    .message("Login successful")
                    .build();

        } catch (DisabledException e) {
            log.warn("Account disabled for user: {}", request.getIdentifier());
            throw new DisabledException("Account is disabled. Please contact administrator.");
        } catch (BadCredentialsException e) {
            log.warn("Invalid credentials for user: {}", request.getIdentifier());
            throw new BadCredentialsException("Invalid username or password");
        } catch (Exception e) {
            log.error("Authentication failed for user: {}", request.getIdentifier(), e);
            throw new BadCredentialsException("Authentication failed");
        }
    }

    /**
     * Refresh access token using refresh token
     */
    @Transactional(readOnly = true)
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String username = jwtTokenProvider.getUsername(refreshToken);
        Sitemanager sitemanager = sitemanagerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        String newAccessToken = jwtTokenProvider.generateToken(authentication);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        log.info("Token refreshed for user: {}", username);

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .expiresIn(86400000L)
                .username(sitemanager.getUsername())
                .email(sitemanager.getEmail())
                .roles(roles)
                .message("Token refreshed successfully")
                .build();
    }

    /**
     * Logout user (clear security context)
     */
    public void logout() {
        SecurityContextHolder.clearContext();
        log.info("User logged out successfully");
    }

    /**
     * Get current authenticated user
     */
    public Sitemanager getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }

        String username = authentication.getName();
        return sitemanagerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
