package com.blockcode.ordersystem.mapper;

import com.blockcode.ordersystem.dto.UserDto;
import com.blockcode.ordersystem.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet()));
        return dto;
    }
}
