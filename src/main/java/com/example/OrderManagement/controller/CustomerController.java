package com.example.OrderManagement.controller;

import com.example.OrderManagement.dto.CustomerDto;
import com.example.OrderManagement.exception.BadRequestException;
import com.example.OrderManagement.service.CustomerService;
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
@RequestMapping("/customers")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get All Customers",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get All Customers!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get All Customers!\"}"),}))
            })
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get Customer by Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get Customer by Id!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get Customer by Id!\"}"),}))
            })
    public ResponseEntity<CustomerDto> getCategoryById(
            @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create Customer",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Successfully Create Customer!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully Create Customer!\"}"),}))
            })
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        if (customerDto.getId() == 0) {
            log.error("Cannot have an ID {}", customerDto);
            throw new BadRequestException(CustomerController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update Customer",
            responses = {
                    @ApiResponse(responseCode = "202",
                            description = "Successfully Update Customer!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update Customer!\"}"),}))
            })
    public ResponseEntity<CustomerDto> updateCategory
            (@Valid @RequestBody CustomerDto customerDto, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(customerService.updateCustomer(customerDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete Customer",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Delete Customer!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Delete Customer!\"}"),}))
            })
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
