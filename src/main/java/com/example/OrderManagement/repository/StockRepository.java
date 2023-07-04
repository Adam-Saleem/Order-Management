package com.example.OrderManagement.repository;


import com.example.OrderManagement.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
