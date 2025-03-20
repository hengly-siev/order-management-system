package com.blockcode.ordersystem.service;

import com.blockcode.ordersystem.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
