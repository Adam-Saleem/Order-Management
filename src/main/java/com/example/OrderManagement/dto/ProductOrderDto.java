package com.example.OrderManagement.dto;

import com.example.OrderManagement.entity.Order;
import com.example.OrderManagement.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductOrderDto {
    private Long id;
    private int quantity;
    private BigDecimal price;
    private BigDecimal vat;
    private Product product;
    private Order order;

}
