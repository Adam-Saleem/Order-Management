package com.example.OrderManagement.service;

import com.example.OrderManagement.dto.ProductOrderDto;
import com.example.OrderManagement.dto.ProductOrderDto;

import java.util.List;

public interface ProductOrderService {
    ProductOrderDto createProductOrder(ProductOrderDto productOrderDto);

    List<ProductOrderDto> getAllProductOrders();

    ProductOrderDto getProductOrderById(Long id);

    ProductOrderDto updateProductOrder(ProductOrderDto productOrderDto, Long id);

    void deleteProductOrderById(Long id);
}
