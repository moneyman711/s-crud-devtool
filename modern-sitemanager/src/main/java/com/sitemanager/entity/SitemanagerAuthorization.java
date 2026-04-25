package com.sitemanager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Sitemanager Authorization Entity
 * Stores granular permissions for sitemanager users
 */
@Entity
@Table(name = "sitemanager_authorization")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SitemanagerAuthorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuthorizationID")
    private Integer authorizationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AdminID", nullable = false, foreignKey = @ForeignKey(name = "FK_AUTH_ADMIN"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Sitemanager sitemanager;

    @Column(name = "ModuleName", nullable = false, length = 100)
    private String moduleName;

    @Column(name = "Authority", nullable = false, length = 100)
    private String authority; // e.g., MODULE_SMS_READ, MODULE_SMS_WRITE

    @Column(name = "Description", length = 255)
    private String description;

    @Column(name = "IsActive", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "CreatedDateTime", updatable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ExpiryDate")
    private LocalDateTime expiryDate;
}
