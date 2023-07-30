package com.example.mappers;

import com.example.dto.CreateProductRequest;
import com.example.model.Product;
import org.springframework.stereotype.Service;

@Service
public class CreateProductRequestMapper {

    public static Product toEntity(CreateProductRequest createProductRequest) {
        return Product.builder()
                .name(createProductRequest.getName())
                .description(createProductRequest.getDescription())
                .price(createProductRequest.getPrice())
                .build();
    }
}
