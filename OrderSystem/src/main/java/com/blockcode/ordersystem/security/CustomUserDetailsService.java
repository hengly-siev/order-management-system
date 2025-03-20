package com.blockcode.ordersystem.security;

import com.blockcode.ordersystem.entity.User;
import com.blockcode.ordersystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Build authorities from roles and permissions.
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Stream.concat(
                        // Prefix roles with "ROLE_" so that you can use both role- and permission-based checks.
                        user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())),
                        // Include individual permissions
                        user.getRoles().stream()
                                .flatMap(role -> role.getPermissions().stream()
                                        .map(permission -> new SimpleGrantedAuthority(permission.getName())))
                ).collect(Collectors.toList())
        );
    }
}