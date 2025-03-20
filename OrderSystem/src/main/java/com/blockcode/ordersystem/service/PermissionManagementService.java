package com.blockcode.ordersystem.service;

import com.blockcode.ordersystem.dto.PermissionDto;

import java.util.List;

public interface PermissionManagementService {
    PermissionDto createPermission(PermissionDto permissionDto);
    PermissionDto updatePermission(Long permissionId, PermissionDto permissionDto);
    void deletePermission(Long permissionId);
    PermissionDto getPermissionById(Long permissionId);
    List<PermissionDto> getAllPermissions();
}
