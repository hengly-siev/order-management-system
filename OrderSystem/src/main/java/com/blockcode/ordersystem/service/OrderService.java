package com.blockcode.ordersystem.service;

import com.blockcode.ordersystem.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto, String username);
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getAllOrders();
}
