package com.blockcode.ordersystem.service.impl;

import com.blockcode.ordersystem.dto.RoleDto;
import com.blockcode.ordersystem.entity.Permission;
import com.blockcode.ordersystem.entity.Role;
import com.blockcode.ordersystem.exception.ResourceNotFoundException;
import com.blockcode.ordersystem.mapper.RoleMapper;
import com.blockcode.ordersystem.repository.PermissionRepository;
import com.blockcode.ordersystem.repository.RoleRepository;
import com.blockcode.ordersystem.service.RoleManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleManagementServiceImpl implements RoleManagementService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleManagementServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        role = roleRepository.save(role);
        return RoleMapper.toDto(role);
    }

    @Override
    public RoleDto updateRole(Long roleId, RoleDto roleDto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        role.setName(roleDto.getName());
        role = roleRepository.save(role);
        return RoleMapper.toDto(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        roleRepository.delete(role);
    }

    @Override
    public RoleDto getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        return RoleMapper.toDto(role);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto assignPermissionToRole(Long roleId, List<Long> permissionIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        List<Permission> permissions = permissionRepository.findAllById(permissionIds);
        if (permissions.size() != permissionIds.size()) {
            throw new ResourceNotFoundException("One or more permission not found");
        }
        role.setPermissions(permissions.stream().collect(Collectors.toSet()));
        role = roleRepository.save(role);
        return RoleMapper.toDto(role);
    }
}
