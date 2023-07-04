package com.example.OrderManagement.service;

import com.example.OrderManagement.dto.StockDto;

import java.util.List;

public interface StockService {
    StockDto createStock(StockDto stockDto);

    List<StockDto> getAllStocks();

    StockDto getStockById(Long id);

    StockDto updateStock(StockDto stockDto, Long id);

    void deleteStockById(Long id);
}
