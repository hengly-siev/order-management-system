package com.blockcode.ordersystem.mapper;

import com.blockcode.ordersystem.dto.OrderDto;
import com.blockcode.ordersystem.entity.Order;

public class OrderMapper {
    public static OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setDetails(order.getDetails());
        return dto;
    }

    public static Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setOrderNumber(dto.getOrderNumber());
        order.setDetails(dto.getDetails());
        return order;
    }
}
