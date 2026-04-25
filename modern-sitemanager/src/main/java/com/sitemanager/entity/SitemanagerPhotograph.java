package com.sitemanager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Sitemanager Photograph Entity
 * Stores profile photographs for sitemanager users
 */
@Entity
@Table(name = "sitemanager_photograph")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SitemanagerPhotograph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PhotoID")
    private Integer photoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AdminID", nullable = false, foreignKey = @ForeignKey(name = "FK_PHOTO_ADMIN"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Sitemanager sitemanager;

    @Column(name = "FileName", nullable = false, length = 255)
    private String fileName;

    @Column(name = "FilePath", nullable = false, length = 500)
    private String filePath;

    @Column(name = "FileSize")
    private Long fileSize; // in bytes

    @Column(name = "ContentType", length = 100)
    private String contentType; // MIME type

    @Column(name = "IsPrimary")
    @Builder.Default
    private Boolean isPrimary = false;

    @Column(name = "Description", length = 255)
    private String description;

    @CreationTimestamp
    @Column(name = "UploadedDateTime", updatable = false)
    private LocalDateTime uploadedDateTime;

    @Column(name = "UploadedBy")
    private Integer uploadedBy;
}
