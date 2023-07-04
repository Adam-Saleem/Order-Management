package com.example.OrderManagement.controller;

import com.example.OrderManagement.dto.StockDto;
import com.example.OrderManagement.exception.BadRequestException;
import com.example.OrderManagement.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private final Logger log = LoggerFactory.getLogger(StockController.class);

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get All Stocks",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get All Stocks!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get All Stocks!\"}"),}))
            })
    public ResponseEntity<List<StockDto>> getAllStocks() {
        return ResponseEntity.ok().body(stockService.getAllStocks());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get Stock by Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get Stock by Id!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get Stock by Id!\"}"),}))
            })
    public ResponseEntity<StockDto> getCategoryById(
            @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create Stock",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Successfully Create Stock!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully Create Stock!\"}"),}))
            })
    public ResponseEntity<StockDto> createStock(@Valid @RequestBody StockDto stockDto) {
        if (stockDto.getId() == 0) {
            log.error("Cannot have an ID {}", stockDto);
            throw new BadRequestException(StockController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(stockService.createStock(stockDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update Stock",
            responses = {
                    @ApiResponse(responseCode = "202",
                            description = "Successfully Update Stock!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update Stock!\"}"),}))
            })
    public ResponseEntity<StockDto> updateCategory
            (@Valid @RequestBody StockDto stockDto, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(stockService.updateStock(stockDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete Stock",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Delete Stock!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Delete Stock!\"}"),}))
            })
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id) {
        stockService.deleteStockById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
