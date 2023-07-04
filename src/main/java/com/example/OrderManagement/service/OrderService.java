package com.example.OrderManagement.service;


import com.example.OrderManagement.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto updateOrder(OrderDto orderDto, Long id);

    void deleteOrderById(Long id);
}
