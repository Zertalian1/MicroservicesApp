package com.example.service;

import com.example.dto.productDto;
import com.example.dto.ProductInfoDto;

import java.util.List;

public interface ProductService {

    Long addProduct(productDto productDto);

    List<ProductInfoDto> getAllProducts();

    ProductInfoDto getProductById(Long id);

    Long editProduct(Long productId, productDto productDto);

    ProductInfoDto deleteProduct(Long productId);
}
