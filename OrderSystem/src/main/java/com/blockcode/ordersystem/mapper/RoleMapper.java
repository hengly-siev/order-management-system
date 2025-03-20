package com.blockcode.ordersystem.mapper;


import com.blockcode.ordersystem.dto.RoleDto;
import com.blockcode.ordersystem.entity.Role;

import java.util.stream.Collectors;

public class RoleMapper {
    public static RoleDto toDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        if (role.getPermissions() != null) {
            dto.setPermissions(role.getPermissions()
                    .stream()
                    .map(PermissionMapper::toDto)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static Role toEntity(RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        return role;
    }
}
