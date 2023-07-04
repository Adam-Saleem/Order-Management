package com.example.OrderManagement.controller;

import com.example.OrderManagement.dto.OrderDto;
import com.example.OrderManagement.exception.BadRequestException;
import com.example.OrderManagement.service.OrderService;
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
@RequestMapping("/orders")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get All Orders",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get All Orders!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get All Orders!\"}"),}))
            })
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get Order by Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get Order by Id!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get Order by Id!\"}"),}))
            })
    public ResponseEntity<OrderDto> getCategoryById(
            @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create Order",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Successfully Create Order!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully Create Order!\"}"),}))
            })
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        if (orderDto.getId() == 0) {
            log.error("Cannot have an ID {}", orderDto);
            throw new BadRequestException(OrderController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update Order",
            responses = {
                    @ApiResponse(responseCode = "202",
                            description = "Successfully Update Order!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update Order!\"}"),}))
            })
    public ResponseEntity<OrderDto> updateCategory
            (@Valid @RequestBody OrderDto orderDto, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(orderService.updateOrder(orderDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete Order",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Delete Order!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Delete Order!\"}"),}))
            })
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
