package com.example.mappers;

import com.example.dto.OrderInfoDto;
import com.example.model.Order;

public class OrderInfoDtoMapper {

    static OrderInfoDto toDto(Order entity) {
        return new OrderInfoDto(
                entity.getId(),
                entity.getOrderDate(),
                entity.getCompletedDate(),
                entity.getComment(),
                entity.getProductsList().stream().map(OrderedProductDtoMapper::toDto).toList()
        );
    }
}
