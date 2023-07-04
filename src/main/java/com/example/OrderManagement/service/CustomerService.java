package com.example.OrderManagement.service;


import com.example.OrderManagement.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto updateCustomer(CustomerDto customerDto, Long id);

    void deleteCustomerById(Long id);
}
