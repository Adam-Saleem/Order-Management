package com.example.OrderManagement.service;

import com.example.OrderManagement.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto updateProduct(ProductDto productDto, Long id);

    void deleteProductById(Long id);
}
