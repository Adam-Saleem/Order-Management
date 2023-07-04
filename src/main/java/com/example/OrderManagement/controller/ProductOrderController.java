package com.example.OrderManagement.controller;

import com.example.OrderManagement.dto.ProductOrderDto;
import com.example.OrderManagement.exception.BadRequestException;
import com.example.OrderManagement.service.ProductOrderService;
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
@RequestMapping("/productOrders")
public class ProductOrderController {
    private final Logger log = LoggerFactory.getLogger(ProductOrderController.class);

    private ProductOrderService productOrderService;

    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get All ProductOrders",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get All ProductOrders!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get All ProductOrders!\"}"),}))
            })
    public ResponseEntity<List<ProductOrderDto>> getAllProductOrders() {
        return ResponseEntity.ok().body(productOrderService.getAllProductOrders());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get ProductOrder by Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get ProductOrder by Id!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get ProductOrder by Id!\"}"),}))
            })
    public ResponseEntity<ProductOrderDto> getCategoryById(
            @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productOrderService.getProductOrderById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create ProductOrder",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Successfully Create ProductOrder!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully Create ProductOrder!\"}"),}))
            })
    public ResponseEntity<ProductOrderDto> createProductOrder(@Valid @RequestBody ProductOrderDto productOrderDto) {
        if (productOrderDto.getId() == 0) {
            log.error("Cannot have an ID {}", productOrderDto);
            throw new BadRequestException(ProductOrderController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(productOrderService.createProductOrder(productOrderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update ProductOrder",
            responses = {
                    @ApiResponse(responseCode = "202",
                            description = "Successfully Update ProductOrder!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update ProductOrder!\"}"),}))
            })
    public ResponseEntity<ProductOrderDto> updateCategory
            (@Valid @RequestBody ProductOrderDto productOrderDto, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productOrderService.updateProductOrder(productOrderDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete ProductOrder",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Delete ProductOrder!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Delete ProductOrder!\"}"),}))
            })
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id) {
        productOrderService.deleteProductOrderById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
