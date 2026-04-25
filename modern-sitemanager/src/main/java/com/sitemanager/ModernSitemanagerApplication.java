package com.sitemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * Modern SiteManager Application
 * Advanced secure web application with Spring Boot 3.x and Vue.js
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableMethodSecurity(prePostEnabled = true)
public class ModernSitemanagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ModernSitemanagerApplication.class, args);
    }
}
