package com.example.mappers;

import com.example.dto.OrderedProductDto;
import com.example.model.OrderedProduct;

public class OrderedProductDtoMapper {

    static OrderedProductDto toDto(OrderedProduct entity){
        return new OrderedProductDto(
                entity.getProduct().getId(),
                entity.getProduct().getName(),
                entity.getPrice()
        );
    }
}
