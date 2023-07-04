package com.example.OrderManagement.dto;

import com.example.OrderManagement.entity.Order;
import lombok.Data;


import java.sql.Date;
import java.util.Set;

@Data
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date bortAt;
    private Set<Order> orders;
}
