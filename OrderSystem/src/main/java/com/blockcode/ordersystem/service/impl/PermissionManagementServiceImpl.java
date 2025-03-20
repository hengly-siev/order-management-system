package com.blockcode.ordersystem.service.impl;

import com.blockcode.ordersystem.dto.PermissionDto;
import com.blockcode.ordersystem.entity.Permission;
import com.blockcode.ordersystem.exception.ResourceNotFoundException;
import com.blockcode.ordersystem.mapper.PermissionMapper;
import com.blockcode.ordersystem.repository.PermissionRepository;
import com.blockcode.ordersystem.service.PermissionManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionManagementServiceImpl implements PermissionManagementService {

    private final PermissionRepository permissionRepository;

    public PermissionManagementServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public PermissionDto createPermission(PermissionDto permissionDto) {
        Permission permission = new Permission();
        permission.setName(permissionDto.getName());
        permission.setDescription(permission.getDescription());
        permission = permissionRepository.save(permission);
        return PermissionMapper.toDto(permission);
    }

    @Override
    public PermissionDto updatePermission(Long permissionId, PermissionDto permissionDto) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id: " + permissionId));
        permission.setName(permissionDto.getName());
        permission.setDescription(permission.getDescription());
        permission = permissionRepository.save(permission);
        return PermissionMapper.toDto(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id: " + permissionId));
        permissionRepository.delete(permission);
    }

    @Override
    public PermissionDto getPermissionById(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with id: " + permissionId));
        return PermissionMapper.toDto(permission);
    }

    @Override
    public List<PermissionDto> getAllPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(PermissionMapper::toDto)
                .collect(Collectors.toList());
    }
}
