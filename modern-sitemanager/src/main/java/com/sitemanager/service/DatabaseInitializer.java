package com.sitemanager.service;

import com.sitemanager.dto.AuthResponse;
import com.sitemanager.dto.LoginRequest;
import com.sitemanager.entity.Sitemanager;
import com.sitemanager.repository.SitemanagerRepository;
import com.sitemanager.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * Database Initialization - Creates default admin user for development
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class DatabaseInitializer {

    private final SitemanagerRepository sitemanagerRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.password.strength:12}")
    private int passwordStrength;

    /**
     * Create default admin user in dev profile only
     */
    @Bean
    @Profile("dev")
    @Transactional
    public CommandLineRunner initDatabase() {
        return args -> {
            log.info("Initializing database with default admin user...");

            // Check if admin user already exists
            if (!sitemanagerRepository.existsByUsername("admin")) {
                Sitemanager admin = Sitemanager.builder()
                        .adminId(1)
                        .firstName("System")
                        .lastName("Administrator")
                        .username("admin")
                        .email("admin@sitemanager.com")
                        .password(passwordEncoder.encode("Admin@123"))
                        .loginRole("SUPERADMIN")
                        .superAdminRight((short) 1)
                        .currentStatus((short) 1)
                        .loginStatus((short) 1)
                        .multiLogin((short) 1)
                        .accessModule("ALL")
                        .build();

                sitemanagerRepository.save(admin);
                log.info("Default admin user created successfully!");
                log.info("Username: admin");
                log.info("Password: Admin@123");
                log.info("===========================================");
            } else {
                log.info("Admin user already exists, skipping initialization");
            }

            // Create demo user
            if (!sitemanagerRepository.existsByUsername("demo")) {
                Sitemanager demoUser = Sitemanager.builder()
                        .adminId(2)
                        .firstName("Demo")
                        .lastName("User")
                        .username("demo")
                        .email("demo@sitemanager.com")
                        .password(passwordEncoder.encode("Demo@123"))
                        .loginRole("USER")
                        .superAdminRight((short) 0)
                        .currentStatus((short) 1)
                        .loginStatus((short) 1)
                        .multiLogin((short) 0)
                        .accessModule("VIEW_ONLY")
                        .build();

                sitemanagerRepository.save(demoUser);
                log.info("Demo user created successfully!");
                log.info("Username: demo");
                log.info("Password: Demo@123");
                log.info("===========================================");
            }
        };
    }
}
