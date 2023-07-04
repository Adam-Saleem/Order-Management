package com.example.OrderManagement.service.imp;


import com.example.OrderManagement.dto.ProductOrderDto;
import com.example.OrderManagement.entity.ProductOrder;
import com.example.OrderManagement.exception.ResourceNotFoundException;
import com.example.OrderManagement.repository.ProductOrderRepository;
import com.example.OrderManagement.service.ProductOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOrderServiceImp implements ProductOrderService {

    private ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImp(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public ProductOrderDto createProductOrder(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = mapToEntity(productOrderDto);
        ProductOrder newProductOrder = productOrderRepository.save(productOrder);

        ProductOrderDto productOrderResponse = mapToDto(newProductOrder);
        return productOrderResponse;
    }

    @Override
    public List<ProductOrderDto> getAllProductOrders() {
        List<ProductOrder> productOrders = productOrderRepository.findAll();
        return productOrders.stream().map(productOrder -> mapToDto(productOrder)).collect(Collectors.toList());
    }

    @Override
    public ProductOrderDto getProductOrderById(Long id) {
        ProductOrder productOrder = productOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
        return mapToDto(productOrder);
    }

    @Override
    public ProductOrderDto updateProductOrder(ProductOrderDto productOrderDto, Long id) {
        ProductOrder productOrder = productOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
        productOrder.setPrice(productOrderDto.getPrice());
        productOrder.setVat(productOrderDto.getVat());
        productOrder.setOrder(productOrderDto.getOrder());
        productOrder.setProduct(productOrderDto.getProduct());
        ProductOrder updatedProductOrder = productOrderRepository.save(productOrder);
        return mapToDto(updatedProductOrder);
    }

    @Override
    public void deleteProductOrderById(Long id) {
        ProductOrder productOrder = productOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
        productOrderRepository.delete(productOrder);
    }

    private ProductOrderDto mapToDto(ProductOrder productOrder) {
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setId(productOrder.getId());
        productOrderDto.setPrice(productOrder.getPrice());
        productOrderDto.setVat(productOrder.getVat());
        productOrderDto.setOrder(productOrder.getOrder());
        productOrderDto.setProduct(productOrder.getProduct());
        return productOrderDto;
    }

    private ProductOrder mapToEntity(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setPrice(productOrderDto.getPrice());
        productOrder.setVat(productOrderDto.getVat());
        productOrder.setOrder(productOrderDto.getOrder());
        productOrder.setProduct(productOrderDto.getProduct());
        return productOrder;
    }
}
