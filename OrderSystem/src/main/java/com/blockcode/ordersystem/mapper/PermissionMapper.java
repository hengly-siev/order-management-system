package com.blockcode.ordersystem.mapper;

import com.blockcode.ordersystem.dto.PermissionDto;
import com.blockcode.ordersystem.entity.Permission;

public class PermissionMapper {
    public static PermissionDto toDto(Permission permission) {
        PermissionDto dto = new PermissionDto();
        dto.setId(permission.getId());
        dto.setName(permission.getName());
        dto.setDescription(permission.getDescription());
        return dto;
    }

    public static Permission toEntity(PermissionDto dto) {
        Permission permission = new Permission();
        permission.setName(dto.getName());
        permission.setDescription(dto.getDescription());
        return permission;
    }
}
