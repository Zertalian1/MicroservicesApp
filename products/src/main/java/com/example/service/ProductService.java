package com.example.service;

import com.example.dto.CreateProductRequest;
import com.example.dto.ProductInfoDto;

import java.util.List;

public interface ProductService {

    int addProduct(CreateProductRequest createProductRequest);

    List<ProductInfoDto> getAllProducts();

    ProductInfoDto getProductById(Long id);
}
