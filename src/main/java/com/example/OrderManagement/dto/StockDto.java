package com.example.OrderManagement.dto;

import com.example.OrderManagement.entity.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockDto {
    private Long id;
    private Product product;
    private int quantity;
    private LocalDateTime updated_at;

}
