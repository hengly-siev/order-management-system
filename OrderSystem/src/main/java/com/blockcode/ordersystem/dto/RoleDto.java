package com.blockcode.ordersystem.dto;

import com.blockcode.ordersystem.entity.Permission;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {
    private Long id;

    @NotBlank(message = "Role name is required")
    private String name;

    private Set<PermissionDto> permissions;

    public RoleDto() {
    }

    public RoleDto(String name, Set<PermissionDto> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Role name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Role name is required") String name) {
        this.name = name;
    }

    public Set<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDto> permissions) {
        this.permissions = permissions;
    }
}
