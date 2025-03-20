package com.blockcode.ordersystem.service.impl;

import com.blockcode.ordersystem.dto.CreateUserDto;
import com.blockcode.ordersystem.dto.UserDto;
import com.blockcode.ordersystem.entity.Role;
import com.blockcode.ordersystem.entity.User;
import com.blockcode.ordersystem.exception.ResourceNotFoundException;
import com.blockcode.ordersystem.mapper.UserMapper;
import com.blockcode.ordersystem.repository.RoleRepository;
import com.blockcode.ordersystem.repository.UserRepository;
import com.blockcode.ordersystem.service.UserManagementService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserManagementServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setUsername(createUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, CreateUserDto createUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setUsername(createUserDto.getUsername());
        if (createUserDto.getPassword() != null && !createUserDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        }
        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto assignRolesToUser(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<Role> roles = roleRepository.findAllById(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new ResourceNotFoundException("One or more roles not found");
        }
        user.setRoles(roles.stream().collect(Collectors.toSet()));
        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }
}
