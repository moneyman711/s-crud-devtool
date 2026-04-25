package com.sitemanager.controller;

import com.sitemanager.entity.Sitemanager;
import com.sitemanager.service.SitemanagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Sitemanager Controller - REST API for sitemanager CRUD operations
 */
@RestController
@RequestMapping("/api/sitemanagers")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Sitemanager", description = "SiteManager management APIs")
public class SitemanagerController {

    private final SitemanagerService sitemanagerService;

    @GetMapping
    @Operation(summary = "Get all sitemanagers", description = "Get paginated list of sitemanagers with optional search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "adminId,desc") String sort,
            @RequestParam(required = false) String search) {
        
        log.info("Fetching sitemanagers - page: {}, size: {}, sort: {}", page, size, sort);
        
        try {
            Sort.Direction direction = sort.endsWith(",asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            String sortBy = sort.split(",")[0];
            
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
            
            Page<Sitemanager> sitemanagerPage;
            
            if (search != null && !search.isEmpty()) {
                sitemanagerPage = sitemanagerService.searchByName(search, pageable);
            } else {
                sitemanagerPage = sitemanagerService.getAll(pageable);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("content", sitemanagerPage.getContent());
            response.put("totalElements", sitemanagerPage.getTotalElements());
            response.put("totalPages", sitemanagerPage.getTotalPages());
            response.put("number", sitemanagerPage.getNumber());
            response.put("size", sitemanagerPage.getSize());
            response.put("numberOfElements", sitemanagerPage.getNumberOfElements());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("Error fetching sitemanagers", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get sitemanager by ID", description = "Get detailed information of a sitemanager")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Sitemanager> getById(@PathVariable Integer id) {
        log.info("Fetching sitemanager by ID: {}", id);
        
        Sitemanager sitemanager = sitemanagerService.getById(id);
        return ResponseEntity.ok(sitemanager);
    }

    @PostMapping
    @Operation(summary = "Create sitemanager", description = "Create a new sitemanager")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Sitemanager> create(@Valid @RequestBody Sitemanager sitemanager) {
        log.info("Creating new sitemanager with username: {}", sitemanager.getUsername());
        
        Sitemanager created = sitemanagerService.create(sitemanager);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update sitemanager", description = "Update an existing sitemanager")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Sitemanager> update(
            @PathVariable Integer id,
            @Valid @RequestBody Sitemanager sitemanagerDetails) {
        log.info("Updating sitemanager with ID: {}", id);
        
        Sitemanager updated = sitemanagerService.update(id, sitemanagerDetails);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sitemanager", description = "Delete a sitemanager")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        log.info("Deleting sitemanager with ID: {}", id);
        
        sitemanagerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search sitemanagers", description = "Search sitemanagers by name")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Map<String, Object>> search(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        log.info("Searching sitemanagers with query: {}", q);
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Sitemanager> result = sitemanagerService.searchByName(q, pageable);
        
        Map<String, Object> response = new HashMap<>();
        response.put("content", result.getContent());
        response.put("totalElements", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    @Operation(summary = "Get sitemanager statistics", description = "Get count and role-based statistics")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Map<String, Object>> getStats() {
        log.info("Fetching sitemanager statistics");
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalActive", sitemanagerService.countActive());
        
        return ResponseEntity.ok(stats);
    }
}
