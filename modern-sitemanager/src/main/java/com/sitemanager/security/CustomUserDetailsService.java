package com.sitemanager.security;

import com.sitemanager.entity.Sitemanager;
import com.sitemanager.repository.SitemanagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Custom UserDetailsService implementation for Spring Security
 * Loads user details from database for authentication
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final SitemanagerRepository sitemanagerRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username: {}", username);

        Sitemanager sitemanager = sitemanagerRepository.findByUsername(username)
            .orElseThrow(() -> {
                log.warn("User not found with username: {}", username);
                return new UsernameNotFoundException(
                    "User not found with username: " + username);
            });

        // Check if account is enabled
        if (!sitemanager.getIsEnabled()) {
            log.warn("Account disabled for username: {}", username);
            throw new UsernameNotFoundException("Account is disabled for username: " + username);
        }

        // Check if login is allowed
        if (sitemanager.getLoginStatus() != null && sitemanager.getLoginStatus() == 0) {
            log.warn("Login disabled for username: {}", username);
            throw new UsernameNotFoundException("Login is disabled for this account");
        }

        log.info("User loaded successfully: {}", username);
        return sitemanager;
    }

    /**
     * Load user by email identifier
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByIdentifier(String identifier) throws UsernameNotFoundException {
        log.debug("Loading user by identifier: {}", identifier);

        Sitemanager sitemanager = sitemanagerRepository.findByIdentifier(identifier)
            .orElseThrow(() -> {
                log.warn("User not found with identifier: {}", identifier);
                return new UsernameNotFoundException(
                    "User not found with identifier: " + identifier);
            });

        if (!sitemanager.getIsEnabled()) {
            log.warn("Account disabled for identifier: {}", identifier);
            throw new UsernameNotFoundException("Account is disabled");
        }

        return sitemanager;
    }
}
