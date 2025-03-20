package com.blockcode.ordersystem.controller;

import com.blockcode.ordersystem.dto.PermissionDto;
import com.blockcode.ordersystem.service.PermissionManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/permissions")
@Tag(name = "Permission Management", description = "Admin operations for managing permissions")
public class PermissionManagementController {

    private static final Logger log = LoggerFactory.getLogger(RoleManagementController.class);

    private final PermissionManagementService permissionManagementService;

    public PermissionManagementController(PermissionManagementService permissionManagementService) {
        this.permissionManagementService = permissionManagementService;
    }

    @Operation(summary = "Create Permission", description = "Creates a new permission")
    @PreAuthorize("hasAuthority('CREATE_PERMISSION')")
    @PostMapping
    public ResponseEntity<PermissionDto> createPermission(@Valid @RequestBody PermissionDto permissionDto) {
        return ResponseEntity.ok(permissionManagementService.createPermission(permissionDto));
    }

    @Operation(summary = "Get Permission", description = "Retrieves a permission by ID")
    @PreAuthorize("hasAuthority('VIEW_PERMISSION')")
    @GetMapping("/{permissionId}")
    public ResponseEntity<PermissionDto> getPermissionById(@PathVariable Long permissionId) {
        return ResponseEntity.ok(permissionManagementService.getPermissionById(permissionId));
    }

    @Operation(summary = "Update Permission", description = "Updates an existing permission")
    @PreAuthorize("hasAuthority('UPDATE_PERMISSION')")
    @PutMapping("/{permissionId}")
    public ResponseEntity<PermissionDto> updatePermission(@PathVariable Long permissionId, @Valid @RequestBody PermissionDto permissionDto) {
        return ResponseEntity.ok(permissionManagementService.updatePermission(permissionId, permissionDto));
    }

    @Operation(summary = "Delete Permission", description = "Deletes a permission by ID")
    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long permissionId) {
        permissionManagementService.deletePermission(permissionId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List Permissions", description = "Lists all permissions")
    @PreAuthorize("hasAuthority('VIEW_PERMISSION')")
    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAllPermissions() {
        return ResponseEntity.ok(permissionManagementService.getAllPermissions());
    }
}
