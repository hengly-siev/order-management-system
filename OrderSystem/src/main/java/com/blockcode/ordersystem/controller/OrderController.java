package com.blockcode.ordersystem.controller;

import com.blockcode.ordersystem.dto.OrderDto;
import com.blockcode.ordersystem.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Operations related to order management")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create Order", description = "Creates a new order using the current user")
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        OrderDto createdOrder = orderService.createOrder(orderDto, username);
        return ResponseEntity.ok(createdOrder);
    }

    @Operation(summary = "Get Order by ID", description = "Retrieves order details by ID")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        OrderDto order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Get All Orders", description = "Retrieves all orders")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
