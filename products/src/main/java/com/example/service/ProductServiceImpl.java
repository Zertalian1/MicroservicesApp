package com.example.service;

import com.example.dto.CreateProductRequest;
import com.example.dto.ProductInfoDto;
import com.example.mappers.CreateProductRequestMapper;
import com.example.mappers.ProductInfoDtoMapper;
import com.example.repository.ProductJdbcTemplateRepository;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record ProductServiceImpl(
        ProductJdbcTemplateRepository productJdbcTemplateRepository,
        ProductRepository productRepository
) implements ProductService{

    @Override
    public int addProduct(CreateProductRequest createProductRequest) {
        return productJdbcTemplateRepository.addProduct(CreateProductRequestMapper.toEntity(createProductRequest));
    }

    @Override
    public List<ProductInfoDto> getAllProducts() {
        List<ProductInfoDto> productList = new ArrayList<>();
        productRepository.findAll().forEach( product ->
                productList.add(ProductInfoDtoMapper.toDto(product))
        );
        return productList;
    }

    @Override
    public ProductInfoDto getProductById(Long id) {
        return productRepository.findById(id).map(ProductInfoDtoMapper::toDto).orElseThrow(
                ()->new RuntimeException("Can not find product")
        );
    }
}
