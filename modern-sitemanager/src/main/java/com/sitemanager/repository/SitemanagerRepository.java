package com.sitemanager.repository;

import com.sitemanager.entity.Sitemanager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Sitemanager Repository - Secure data access layer
 * Extends JpaRepository for CRUD operations with built-in SQL injection protection
 */
@Repository
public interface SitemanagerRepository extends JpaRepository<Sitemanager, Integer> {

    /**
     * Find sitemanager by username (for authentication)
     */
    Optional<Sitemanager> findByUsername(String username);

    /**
     * Find sitemanager by email
     */
    Optional<Sitemanager> findByEmail(String email);

    /**
     * Find sitemanager by employee code
     */
    Optional<Sitemanager> findByEmpCode(String empCode);

    /**
     * Find sitemanager by mobile number
     */
    Optional<Sitemanager> findByMobile(String mobile);

    /**
     * Check if username exists
     */
    boolean existsByUsername(String username);

    /**
     * Check if email exists
     */
    boolean existsByEmail(String email);

    /**
     * Check if employee code exists
     */
    boolean existsByEmpCode(String empCode);

    /**
     * Find active sitemanagers by login role
     */
    List<Sitemanager> findByLoginRoleAndCurrentStatus(String loginRole, Short currentStatus);

    /**
     * Find sitemanagers by status with pagination
     */
    Page<Sitemanager> findByCurrentStatus(Short currentStatus, Pageable pageable);

    /**
     * Search sitemanagers by name (first, middle, or last)
     */
    @Query("SELECT s FROM Sitemanager s WHERE " +
           "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.middleName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Sitemanager> searchByName(@Param("searchTerm") String searchTerm, Pageable pageable);

    /**
     * Find sitemanagers by city
     */
    List<Sitemanager> findByCityOrderByLastNameAsc(String city);

    /**
     * Find sitemanagers by state
     */
    List<Sitemanager> findByStateOrderByLastNameAsc(String state);

    /**
     * Count active sitemanagers
     */
    long countByCurrentStatus(Short currentStatus);

    /**
     * Find sitemanagers who can multi-login
     */
    List<Sitemanager> findByMultiLoginAndCurrentStatus(Short multiLogin, Short currentStatus);

    /**
     * Find sitemanagers with super admin rights
     */
    List<Sitemanager> findBySuperAdminRightAndCurrentStatus(Short superAdminRight, Short currentStatus);

    /**
     * Find sitemanagers by username or email (for forgot password)
     */
    @Query("SELECT s FROM Sitemanager s WHERE " +
           "(s.username = :identifier OR s.email = :identifier) AND s.currentStatus = 1")
    Optional<Sitemanager> findByIdentifier(@Param("identifier") String identifier);
}
