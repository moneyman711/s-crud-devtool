package com.sitemanager.service;

import com.sitemanager.entity.Sitemanager;
import com.sitemanager.exception.ResourceNotFoundException;
import com.sitemanager.repository.SitemanagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Sitemanager Service - Business logic for sitemanager operations
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SitemanagerService {

    private final SitemanagerRepository sitemanagerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Get all sitemanagers with pagination
     */
    @Transactional(readOnly = true)
    public Page<Sitemanager> getAll(Pageable pageable) {
        log.debug("Fetching all sitemanagers with pagination");
        return sitemanagerRepository.findAll(pageable);
    }

    /**
     * Get sitemanager by ID
     */
    @Transactional(readOnly = true)
    public Sitemanager getById(Integer id) {
        log.debug("Fetching sitemanager by ID: {}", id);
        return sitemanagerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sitemanager not found with id: " + id));
    }

    /**
     * Get sitemanager by username
     */
    @Transactional(readOnly = true)
    public Sitemanager getByUsername(String username) {
        log.debug("Fetching sitemanager by username: {}", username);
        return sitemanagerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Sitemanager not found with username: " + username));
    }

    /**
     * Search sitemanagers by name
     */
    @Transactional(readOnly = true)
    public Page<Sitemanager> searchByName(String searchTerm, Pageable pageable) {
        log.debug("Searching sitemanagers by name: {}", searchTerm);
        return sitemanagerRepository.searchByName(searchTerm, pageable);
    }

    /**
     * Create new sitemanager
     */
    @Transactional
    public Sitemanager create(Sitemanager sitemanager) {
        log.info("Creating new sitemanager with username: {}", sitemanager.getUsername());

        // Check if username already exists
        if (sitemanagerRepository.existsByUsername(sitemanager.getUsername())) {
            throw new RuntimeException("Username already exists: " + sitemanager.getUsername());
        }

        // Check if email already exists
        if (sitemanager.getEmail() != null && sitemanagerRepository.existsByEmail(sitemanager.getEmail())) {
            throw new RuntimeException("Email already exists: " + sitemanager.getEmail());
        }

        // Encode password
        if (sitemanager.getPassword() != null && !sitemanager.getPassword().isEmpty()) {
            sitemanager.setPassword(passwordEncoder.encode(sitemanager.getPassword()));
        }

        // Set defaults
        if (sitemanager.getPasswordType() == null) {
            sitemanager.setPasswordType((short) 1); // BCrypt
        }
        if (sitemanager.getCurrentStatus() == null) {
            sitemanager.setCurrentStatus((short) 1);
        }
        if (sitemanager.getLoginStatus() == null) {
            sitemanager.setLoginStatus((short) 1);
        }
        if (sitemanager.getSuperAdminRight() == null) {
            sitemanager.setSuperAdminRight((short) 0);
        }
        if (sitemanager.getMultiLogin() == null) {
            sitemanager.setMultiLogin((short) 0);
        }
        if (sitemanager.getLoginRole() == null) {
            sitemanager.setLoginRole("USER");
        }

        sitemanager.setCreatedDateTime(LocalDateTime.now());
        
        Sitemanager saved = sitemanagerRepository.save(sitemanager);
        log.info("Sitemanager created successfully with ID: {}", saved.getAdminId());
        return saved;
    }

    /**
     * Update existing sitemanager
     */
    @Transactional
    public Sitemanager update(Integer id, Sitemanager sitemanagerDetails) {
        log.info("Updating sitemanager with ID: {}", id);

        Sitemanager sitemanager = getById(id);

        // Update fields
        if (sitemanagerDetails.getFirstName() != null) {
            sitemanager.setFirstName(sitemanagerDetails.getFirstName());
        }
        if (sitemanagerDetails.getMiddleName() != null) {
            sitemanager.setMiddleName(sitemanagerDetails.getMiddleName());
        }
        if (sitemanagerDetails.getLastName() != null) {
            sitemanager.setLastName(sitemanagerDetails.getLastName());
        }
        if (sitemanagerDetails.getEmail() != null) {
            // Check if new email is already taken by another user
            if (!sitemanager.getEmail().equals(sitemanagerDetails.getEmail()) && 
                sitemanagerRepository.existsByEmail(sitemanagerDetails.getEmail())) {
                throw new RuntimeException("Email already exists: " + sitemanagerDetails.getEmail());
            }
            sitemanager.setEmail(sitemanagerDetails.getEmail());
        }
        if (sitemanagerDetails.getMobile() != null) {
            sitemanager.setMobile(sitemanagerDetails.getMobile());
        }
        if (sitemanagerDetails.getAddress() != null) {
            sitemanager.setAddress(sitemanagerDetails.getAddress());
        }
        if (sitemanagerDetails.getCity() != null) {
            sitemanager.setCity(sitemanagerDetails.getCity());
        }
        if (sitemanagerDetails.getState() != null) {
            sitemanager.setState(sitemanagerDetails.getState());
        }
        if (sitemanagerDetails.getPin() != null) {
            sitemanager.setPin(sitemanagerDetails.getPin());
        }
        if (sitemanagerDetails.getLandline() != null) {
            sitemanager.setLandline(sitemanagerDetails.getLandline());
        }
        if (sitemanagerDetails.getGender() != null) {
            sitemanager.setGender(sitemanagerDetails.getGender());
        }
        if (sitemanagerDetails.getBirthDate() != null) {
            sitemanager.setBirthDate(sitemanagerDetails.getBirthDate());
        }
        if (sitemanagerDetails.getMaritalStatus() != null) {
            sitemanager.setMaritalStatus(sitemanagerDetails.getMaritalStatus());
        }
        if (sitemanagerDetails.getEmpCode() != null) {
            if (!sitemanager.getEmpCode().equals(sitemanagerDetails.getEmpCode()) && 
                sitemanagerRepository.existsByEmpCode(sitemanagerDetails.getEmpCode())) {
                throw new RuntimeException("Employee code already exists: " + sitemanagerDetails.getEmpCode());
            }
            sitemanager.setEmpCode(sitemanagerDetails.getEmpCode());
        }
        if (sitemanagerDetails.getJoiningDate() != null) {
            sitemanager.setJoiningDate(sitemanagerDetails.getJoiningDate());
        }
        if (sitemanagerDetails.getLeavingDate() != null) {
            sitemanager.setLeavingDate(sitemanagerDetails.getLeavingDate());
        }
        if (sitemanagerDetails.getLoginRole() != null) {
            sitemanager.setLoginRole(sitemanagerDetails.getLoginRole());
        }
        if (sitemanagerDetails.getAccessModule() != null) {
            sitemanager.setAccessModule(sitemanagerDetails.getAccessModule());
        }
        if (sitemanagerDetails.getMenuType() != null) {
            sitemanager.setMenuType(sitemanagerDetails.getMenuType());
        }
        if (sitemanagerDetails.getCurrentStatus() != null) {
            sitemanager.setCurrentStatus(sitemanagerDetails.getCurrentStatus());
        }
        if (sitemanagerDetails.getLoginStatus() != null) {
            sitemanager.setLoginStatus(sitemanagerDetails.getLoginStatus());
        }
        if (sitemanagerDetails.getMultiLogin() != null) {
            sitemanager.setMultiLogin(sitemanagerDetails.getMultiLogin());
        }
        if (sitemanagerDetails.getSuperAdminRight() != null) {
            sitemanager.setSuperAdminRight(sitemanagerDetails.getSuperAdminRight());
        }

        // Update password if provided
        if (sitemanagerDetails.getPassword() != null && !sitemanagerDetails.getPassword().isEmpty()) {
            sitemanager.setPassword(passwordEncoder.encode(sitemanagerDetails.getPassword()));
        }

        sitemanager.setUpdateDateTime(new java.sql.Timestamp(System.currentTimeMillis()));

        Sitemanager updated = sitemanagerRepository.save(sitemanager);
        log.info("Sitemanager updated successfully with ID: {}", updated.getAdminId());
        return updated;
    }

    /**
     * Delete sitemanager
     */
    @Transactional
    public void delete(Integer id) {
        log.info("Deleting sitemanager with ID: {}", id);
        
        Sitemanager sitemanager = getById(id);
        sitemanagerRepository.delete(sitemanager);
        
        log.info("Sitemanager deleted successfully with ID: {}", id);
    }

    /**
     * Change password
     */
    @Transactional
    public void changePassword(Integer id, String newPassword) {
        log.info("Changing password for sitemanager ID: {}", id);
        
        Sitemanager sitemanager = getById(id);
        sitemanager.setPassword(passwordEncoder.encode(newPassword));
        sitemanager.setPasswordType((short) 1); // BCrypt
        sitemanager.setUpdateDateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        
        sitemanagerRepository.save(sitemanager);
        log.info("Password changed successfully for sitemanager ID: {}", id);
    }

    /**
     * Count active sitemanagers
     */
    @Transactional(readOnly = true)
    public long countActive() {
        return sitemanagerRepository.countByCurrentStatus((short) 1);
    }

    /**
     * Get sitemanagers by role
     */
    @Transactional(readOnly = true)
    public List<Sitemanager> getByRole(String loginRole) {
        return sitemanagerRepository.findByLoginRoleAndCurrentStatus(loginRole, (short) 1);
    }
}
