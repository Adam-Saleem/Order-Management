package com.example.OrderManagement.dto;

import com.example.OrderManagement.entity.Customer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private Customer customer;
    private LocalDateTime ordered_at;
}
