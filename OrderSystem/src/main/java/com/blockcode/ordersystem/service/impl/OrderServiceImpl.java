package com.blockcode.ordersystem.service.impl;

import com.blockcode.ordersystem.dto.OrderDto;
import com.blockcode.ordersystem.entity.Order;
import com.blockcode.ordersystem.entity.User;
import com.blockcode.ordersystem.exception.ResourceNotFoundException;
import com.blockcode.ordersystem.mapper.OrderMapper;
import com.blockcode.ordersystem.repository.OrderRepository;
import com.blockcode.ordersystem.repository.UserRepository;
import com.blockcode.ordersystem.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto, String username) {
        Order order = OrderMapper.toEntity(orderDto);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        order.setCreatedBy(user);
        Order saveOrder = orderRepository.save(order);
        return OrderMapper.toDto(saveOrder);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        return OrderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }
}
