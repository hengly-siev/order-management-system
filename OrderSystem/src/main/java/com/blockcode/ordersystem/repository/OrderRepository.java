package com.blockcode.ordersystem.repository;

import com.blockcode.ordersystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
