package com.blockcode.ordersystem.repository;

import com.blockcode.ordersystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
