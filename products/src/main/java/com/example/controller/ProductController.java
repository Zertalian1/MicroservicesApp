package com.example.controller;

import com.example.dto.productDto;
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
    public Long addNewProduct(
            @RequestBody productDto productDto
    ) {
        return productService.addProduct(productDto);
    }

    @PatchMapping("/{productId}")
    public Long editProduct(
            @RequestBody productDto productDto,
            @PathVariable Long productId
    ) {
        return productService.editProduct(productId, productDto);
    }

    @DeleteMapping("/{productId}")
    public ProductInfoDto deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
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
