package com.example.OrderManagement.service.imp;


import com.example.OrderManagement.dto.ProductDto;
import com.example.OrderManagement.entity.Product;
import com.example.OrderManagement.exception.ResourceNotFoundException;
import com.example.OrderManagement.repository.ProductRepository;
import com.example.OrderManagement.service.ProductService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product newProduct = productRepository.save(product);

        ProductDto productResponse = mapToDto(newProduct);
        return productResponse;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        product.setSlug(productDto.getSlug());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setVat(productDto.getVat());
        product.setReference(productDto.getReference());
        product.setStockable(productDto.getStockable());
        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }

    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setSlug(product.getSlug());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setVat(product.getVat());
        productDto.setReference(product.getReference());
        productDto.setStockable(product.getStockable());
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setSlug(productDto.getSlug());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setVat(productDto.getVat());
        product.setReference(productDto.getReference());
        product.setStockable(productDto.getStockable());
        return product;
    }
}
