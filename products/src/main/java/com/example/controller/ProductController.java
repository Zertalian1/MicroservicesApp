package com.example.controller;

import com.example.dto.CreateProductRequest;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public int addNewProduct(CreateProductRequest createProductRequest){
        return productService.addProduct(createProductRequest);
    }
}
