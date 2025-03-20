package com.blockcode.ordersystem.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String orderNumber;
    private String details;

    public OrderDto() {
    }

    public OrderDto(String orderNumber, String details) {
        this.orderNumber = orderNumber;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
