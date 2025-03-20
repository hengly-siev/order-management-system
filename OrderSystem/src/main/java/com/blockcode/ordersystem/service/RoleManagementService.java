package com.blockcode.ordersystem.service;

import com.blockcode.ordersystem.dto.RoleDto;

import java.util.List;

public interface RoleManagementService {
    RoleDto createRole(RoleDto roleDto);
    RoleDto updateRole(Long roleId, RoleDto roleDto);
    void deleteRole(Long roleId);
    RoleDto getRoleById(Long roleId);
    List<RoleDto> getAllRoles();
    RoleDto assignPermissionToRole(Long roleId, List<Long> permissionIds);
}
