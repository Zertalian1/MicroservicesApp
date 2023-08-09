package com.example.mappers;

import com.example.dto.ProductInfoDto;
import com.example.model.Product;

public class ProductInfoDtoMapper {

    public static ProductInfoDto toDto(Product entity) {
        return new ProductInfoDto(
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
