package com.blockcode.ordersystem.service.impl;

import com.blockcode.ordersystem.entity.User;
import com.blockcode.ordersystem.repository.UserRepository;
import com.blockcode.ordersystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
