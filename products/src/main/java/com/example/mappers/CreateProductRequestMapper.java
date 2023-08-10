package com.example.mappers;

import com.example.dto.productDto;
import com.example.model.Product;
import org.springframework.stereotype.Service;

@Service
public class CreateProductRequestMapper {

    public static Product toEntity(productDto productDto) {
        return Product.builder()
                .name(productDto.name())
                .description(productDto.description())
                .price(productDto.price())
                .build();
    }
}
