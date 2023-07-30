package com.example.service;

import com.example.dto.CreateProductRequest;
import com.example.mappers.CreateProductRequestMapper;
import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public int addProduct(CreateProductRequest createProductRequest) {
        return productRepository.addProduct(CreateProductRequestMapper.toEntity(createProductRequest));
    }
}
