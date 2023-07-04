package com.example.OrderManagement.service.imp;


import com.example.OrderManagement.dto.StockDto;
import com.example.OrderManagement.entity.Stock;
import com.example.OrderManagement.exception.ResourceNotFoundException;
import com.example.OrderManagement.repository.StockRepository;
import com.example.OrderManagement.service.StockService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImp implements StockService {

    private StockRepository stockRepository;

    public StockServiceImp(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockDto createStock(StockDto stockDto) {
        Stock stock = mapToEntity(stockDto);
        Stock newStock = stockRepository.save(stock);

        StockDto stockResponse = mapToDto(newStock);
        return stockResponse;
    }

    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(stock -> mapToDto(stock)).collect(Collectors.toList());
    }

    @Override
    public StockDto getStockById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
        return mapToDto(stock);
    }

    @Override
    public StockDto updateStock(StockDto stockDto, Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
        stock.setProduct(stockDto.getProduct());
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdated_at(stockDto.getUpdated_at());
        Stock updatedStock = stockRepository.save(stock);
        return mapToDto(updatedStock);
    }

    @Override
    public void deleteStockById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
        stockRepository.delete(stock);
    }

    private StockDto mapToDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setProduct(stock.getProduct());
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setUpdated_at(stock.getUpdated_at());
        return stockDto;
    }

    private Stock mapToEntity(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setProduct(stockDto.getProduct());
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdated_at(stockDto.getUpdated_at());
        return stock;
    }
}
