package com.example.controller;

import com.example.dto.CreateProductRequest;
import com.example.dto.ProductInfoDto;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public int addNewProduct(CreateProductRequest createProductRequest) {
        return productService.addProduct(createProductRequest);
    }

    @GetMapping("/all")
    public List<ProductInfoDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductInfoDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
