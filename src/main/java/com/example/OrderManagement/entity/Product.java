package com.example.OrderManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
//import java.util.List;
//import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String slug;

    @Column
    private String name;

    @Column
    private String reference;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal vat;

    @Column
    private Boolean stockable;
}

