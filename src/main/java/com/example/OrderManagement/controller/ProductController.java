package com.example.OrderManagement.controller;

import com.example.OrderManagement.dto.ProductDto;
import com.example.OrderManagement.exception.BadRequestException;
import com.example.OrderManagement.service.ProductService;
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
@RequestMapping("/products")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get All Products",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get All Products!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get All Products!\"}"),}))
            })
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Get Product by Id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Get Product by Id!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Get Product by Id!\"}"),}))
            })
    public ResponseEntity<ProductDto> getCategoryById(
            @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Create Product",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Successfully Create Product!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 201, \"Status\" : \"Created!\", \"Message\" :\"Successfully Create Product!\"}"),}))
            })
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        if (productDto.getId() == 0) {
            log.error("Cannot have an ID {}", productDto);
            throw new BadRequestException(ProductController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Update Product",
            responses = {
                    @ApiResponse(responseCode = "202",
                            description = "Successfully Update Product!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 202, \"Status\" : \"Accepted!\", \"Message\" :\"Successfully Update Product!\"}"),}))
            })
    public ResponseEntity<ProductDto> updateCategory
            (@Valid @RequestBody ProductDto productDto, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.updateProduct(productDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = @SecurityRequirement(name = "token"),
            description = "Delete Product",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully Delete Product!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Delete Product!\"}"),}))
            })
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
