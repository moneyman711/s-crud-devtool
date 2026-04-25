package com.sitemanager.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * JWT Token Provider - Generates and validates JWT tokens
 * Secure implementation with HS512 signing algorithm
 */
@Component
@Slf4j
public class JwtTokenProvider {

    private final Key secretKey;
    private final long validityInMilliseconds;
    private final long refreshValidityInMilliseconds;
    private final String issuer;
    private final String audience;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-ms}") long validityInMilliseconds,
            @Value("${jwt.refresh-expiration-ms}") long refreshValidityInMilliseconds,
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.audience}") String audience) {
        
        // Ensure secret is at least 32 characters for HS256, 64 for HS512
        if (secret.length() < 64) {
            throw new IllegalArgumentException("JWT secret must be at least 64 characters long");
        }
        
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.validityInMilliseconds = validityInMilliseconds;
        this.refreshValidityInMilliseconds = refreshValidityInMilliseconds;
        this.issuer = issuer;
        this.audience = audience;
    }

    /**
     * Generate access token
     */
    public String generateToken(Authentication authentication) {
        log.debug("Generating JWT token for user: {}", authentication.getName());
        
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuer(issuer)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Generate refresh token
     */
    public String generateRefreshToken(Authentication authentication) {
        log.debug("Generating JWT refresh token for user: {}", authentication.getName());
        
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuer(issuer)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshValidityInMilliseconds))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Get username from token
     */
    public String getUsername(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * Get authorities from token
     */
    public java.util.List<String> getAuthorities(String token) {
        Claims claims = parseClaims(token);
        return claims.get("authorities", java.util.List.class);
    }

    /**
     * Validate token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .requireIssuer(issuer)
                    .requireAudience(audience)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Check if token is expired
     */
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = parseClaims(token);
            return claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    /**
     * Parse claims from token
     */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Refresh token if it's about to expire
     */
    public String refreshToken(String token) {
        if (!isTokenExpired(token)) {
            Claims claims = parseClaims(token);
            String username = claims.getSubject();
            
            // Create new token with same authorities
            Authentication auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                    username, null, 
                    getAuthorities(token).stream()
                            .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                            .collect(java.util.stream.Collectors.toList())
            );
            
            return generateToken(auth);
        }
        return null;
    }
}
