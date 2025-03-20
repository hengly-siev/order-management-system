package com.blockcode.ordersystem.controller;

import com.blockcode.ordersystem.dto.CreateUserDto;
import com.blockcode.ordersystem.dto.UserDto;
import com.blockcode.ordersystem.service.UserManagementService;
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
@RequestMapping("/api/admin/users")
@Tag(name = "User Management", description = "Admin operations for managing users")
public class UserManagementController {

    private static final Logger log = LoggerFactory.getLogger(RoleManagementController.class);

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Operation(summary = "Create User", description = "Creates a new user")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userManagementService.createUser(createUserDto));
    }

    @Operation(summary = "Get All User", description = "Retrieves all users")
    @PreAuthorize("hasAuthority('VIEW_USER')")
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @Operation(summary = "Get User", description = "Retrieves a user by ID")
    @PreAuthorize("hasAuthority('VIEW_USER')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userManagementService.getUserById(userId));
    }

    @Operation(summary = "Update User", description = "Updates an existing user")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(userManagementService.updateUser(userId, createUserDto));
    }

    @Operation(summary = "Delete User", description = "Deletes a user by ID")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userManagementService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Assign Roles", description = "Assigns roles to a user")
    @PreAuthorize("hasAuthority('ASSIGN_ROLE')")
    @PostMapping("/{userId}/roles")
    public ResponseEntity<UserDto> assignRolesToUser(@PathVariable Long userId, @RequestBody List<Long> roleIds) {
        return ResponseEntity.ok(userManagementService.assignRolesToUser(userId, roleIds));
    }
}
