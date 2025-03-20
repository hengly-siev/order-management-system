package com.blockcode.ordersystem.service;

import com.blockcode.ordersystem.dto.CreateUserDto;
import com.blockcode.ordersystem.dto.UserDto;

import java.util.List;

public interface UserManagementService {
    UserDto createUser(CreateUserDto createUserDto);
    UserDto updateUser(Long userId, CreateUserDto createUserDto);
    void deleteUser(Long userId);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto assignRolesToUser(Long userId, List<Long> roleIds);
}
