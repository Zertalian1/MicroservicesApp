package com.example.service;

import com.example.dto.productDto;
import com.example.dto.ProductInfoDto;
import com.example.mappers.CreateProductRequestMapper;
import com.example.mappers.ProductInfoDtoMapper;
import com.example.model.Product;
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
    public Long addProduct(productDto productDto) {
        return productJdbcTemplateRepository.addProduct(CreateProductRequestMapper.toEntity(productDto));
    }

    @Override
    public List<ProductInfoDto> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return productList.stream().map(ProductInfoDtoMapper::toDto).toList();
    }

    @Override
    public ProductInfoDto getProductById(Long id) {
        return productRepository.findById(id).map(ProductInfoDtoMapper::toDto).orElseThrow(
                ()->new RuntimeException("Can not find product")
        );
    }

    @Override
    public Long editProduct(Long productId, productDto productDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Can not find product"));
        product.setName(product.getName());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        return productRepository.save(product).getId();
    }

    @Override
    public ProductInfoDto deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Can not find product"));
        productRepository.deleteById(productId);
        return ProductInfoDtoMapper.toDto(product);
    }
}
