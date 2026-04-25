package com.sitemanager.repository;

import com.sitemanager.entity.SitemanagerAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Sitemanager Authorization Repository
 */
@Repository
public interface SitemanagerAuthorizationRepository extends JpaRepository<SitemanagerAuthorization, Integer> {

    /**
     * Find all active authorizations for a sitemanager
     */
    List<SitemanagerAuthorization> findBySitemanager_AdminIdAndIsActive(Integer adminId, Boolean isActive);

    /**
     * Check if authorization exists
     */
    boolean existsBySitemanager_AdminIdAndModuleNameAndAuthority(
        Integer adminId, String moduleName, String authority);

    /**
     * Delete authorizations by admin ID
     */
    @Modifying
    @Query("DELETE FROM SitemanagerAuthorization sa WHERE sa.sitemanager.adminId = :adminId")
    void deleteByAdminId(@Param("adminId") Integer adminId);

    /**
     * Find authorizations by module name
     */
    List<SitemanagerAuthorization> findByModuleName(String moduleName);

    /**
     * Count active authorizations
     */
    long countByIsActive(Boolean isActive);
}
