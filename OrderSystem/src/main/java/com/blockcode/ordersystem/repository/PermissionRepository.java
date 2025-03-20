package com.blockcode.ordersystem.repository;

import com.blockcode.ordersystem.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
