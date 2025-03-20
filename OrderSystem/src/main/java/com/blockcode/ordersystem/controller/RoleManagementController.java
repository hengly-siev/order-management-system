package com.blockcode.ordersystem.controller;

import com.blockcode.ordersystem.dto.RoleDto;
import com.blockcode.ordersystem.service.RoleManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/roles")
@Tag(name = "Role Management", description = "Admin operations for managing roles")
public class RoleManagementController {

    private static final Logger log = LoggerFactory.getLogger(RoleManagementController.class);

    private final RoleManagementService roleManagementService;

    public RoleManagementController(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    @Operation(summary = "Create Role", description = "Creates a new role")
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    @PostMapping
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleManagementService.createRole(roleDto));
    }

    @Operation(summary = "Get Role", description = "Retrieves a role by ID")
    @PreAuthorize("hasAuthority('VIEW_ROLE')")
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long roleId) {
        return ResponseEntity.ok(roleManagementService.getRoleById(roleId));
    }

    @Operation(summary = "Update Role", description = "Updates an existing role")
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long roleId, @Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleManagementService.updateRole(roleId, roleDto));
    }

    @Operation(summary = "Delete Role", description = "Deletes a role by ID")
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        roleManagementService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Assign Permissions", description = "Assigns permissions to a role")
    @PreAuthorize("hasAuthority('ASSIGN_PERMISSION')")
    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<RoleDto> assignPermissionsToRole(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        return ResponseEntity.ok(roleManagementService.assignPermissionToRole(roleId, permissionIds));
    }
}

