package com.example.OrderManagement.service.imp;


import com.example.OrderManagement.dto.OrderDto;
import com.example.OrderManagement.entity.Order;
import com.example.OrderManagement.exception.ResourceNotFoundException;
import com.example.OrderManagement.repository.OrderRepository;
import com.example.OrderManagement.service.OrderService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = mapToEntity(orderDto);
        Order newOrder = orderRepository.save(order);

        OrderDto orderResponse = mapToDto(newOrder);
        return orderResponse;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> mapToDto(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return mapToDto(order);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        order.setCustomer(orderDto.getCustomer());
        order.setOrdered_at(orderDto.getOrdered_at());
        Order updatedOrder = orderRepository.save(order);
        return mapToDto(updatedOrder);
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        orderRepository.delete(order);
    }

    private OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomer(order.getCustomer());
        orderDto.setOrdered_at(order.getOrdered_at());
        return orderDto;
    }

    private Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomer(orderDto.getCustomer());
        order.setOrdered_at(orderDto.getOrdered_at());
        return order;
    }
}
