package com.example.OrderManagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String slug;
    private String name;
    private String reference;
    private BigDecimal price;
    private BigDecimal vat;
    private Boolean stockable;
}
