package com.sitemanager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Sitemanager Entity - Modern secure version of legacy SitemanagerBean
 * Maps to the sitemanager table with enhanced security features
 */
@Entity
@Table(name = "sitemanager")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sitemanager implements UserDetails {

    @Id
    @Column(name = "AdminID")
    private Integer adminId;

    @Column(name = "FirstName", nullable = false, length = 100)
    private String firstName;

    @Column(name = "MiddleName", length = 100)
    private String middleName;

    @Column(name = "LastName", length = 100)
    private String lastName;

    @Column(name = "Gender", length = 10)
    private String gender;

    @Column(name = "BirthDate")
    private Date birthDate;

    @Column(name = "MaritalStatus", length = 20)
    private String maritalStatus;

    @Column(name = "EmpCode", unique = true, length = 50)
    private String empCode;

    @Column(name = "JoiningDate")
    private Date joiningDate;

    @Column(name = "LeavingDate")
    private Date leavingDate;

    @Column(name = "Address", length = 500)
    private String address;

    @Column(name = "City", length = 100)
    private String city;

    @Column(name = "State", length = 100)
    private String state;

    @Column(name = "PIN", length = 20)
    private String pin;

    @Column(name = "Landline", length = 20)
    private String landline;

    @Column(name = "Mobile", length = 20)
    private String mobile;

    @Column(name = "Email", unique = true, length = 255)
    private String email;

    @Column(name = "Username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "Password", nullable = false, length = 255)
    @ToString.Exclude
    private String password;

    @Column(name = "PasswordType", nullable = false)
    @Builder.Default
    private Short passwordType = 1; // 1=BCrypt, 2=SHA256, etc.

    @Column(name = "AccessModule", length = 1000)
    private String accessModule;

    @Column(name = "SuperAdminRight", nullable = false)
    @Builder.Default
    private Short superAdminRight = 0;

    @Column(name = "LoginRole", nullable = false, length = 50)
    @Builder.Default
    private String loginRole = "USER";

    @Column(name = "CurrentStatus", nullable = false)
    @Builder.Default
    private Short currentStatus = 1; // 1=Active, 0=Inactive

    @Column(name = "LoginStatus", nullable = false)
    @Builder.Default
    private Short loginStatus = 1; // 1=Enabled, 0=Disabled

    @Column(name = "MultiLogin", nullable = false)
    @Builder.Default
    private Short multiLogin = 0; // 1=Allowed, 0=Not Allowed

    @Column(name = "MenuType", length = 50)
    private String menuType;

    @Column(name = "UpdateDateTime")
    @UpdateTimestamp
    private Timestamp updateDateTime;

    @CreationTimestamp
    @Column(name = "CreatedDateTime", updatable = false)
    private LocalDateTime createdDateTime;

    @OneToMany(mappedBy = "sitemanager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SitemanagerAuthorization> authorizations = new HashSet<>();

    @OneToMany(mappedBy = "sitemanager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SitemanagerPhotograph> photographs = new HashSet<>();

    // Spring Security UserDetails implementation
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        // Add base role
        authorities.add(new SimpleGrantedAuthority("ROLE_" + loginRole));
        
        // Add super admin role if applicable
        if (superAdminRight != null && superAdminRight == 1) {
            authorities.add(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
        }
        
        // Add module-specific authorities from accessModule field
        if (accessModule != null && !accessModule.trim().isEmpty()) {
            String[] modules = accessModule.split(",");
            for (String module : modules) {
                String moduleName = module.trim().toUpperCase();
                if (!moduleName.isEmpty()) {
                    authorities.add(new SimpleGrantedAuthority("MODULE_" + moduleName));
                }
            }
        }
        
        // Add dynamic authorities from authorization table
        if (authorizations != null) {
            authorities.addAll(
                authorizations.stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                    .collect(Collectors.toSet())
            );
        }
        
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return currentStatus != null && currentStatus == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return loginStatus != null && loginStatus == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Can be enhanced with password expiry logic
    }

    @Override
    public boolean isEnabled() {
        return currentStatus != null && currentStatus == 1 
            && loginStatus != null && loginStatus == 1;
    }

    // Helper methods
    
    public String getFullName() {
        StringBuilder fullName = new StringBuilder(firstName);
        if (middleName != null && !middleName.isEmpty()) {
            fullName.append(" ").append(middleName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            fullName.append(" ").append(lastName);
        }
        return fullName.toString();
    }
}
