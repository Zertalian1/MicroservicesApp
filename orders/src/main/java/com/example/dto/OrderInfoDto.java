package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderInfoDto {
    private final Long id;
    private final LocalDateTime orderDate;
    private final LocalDateTime completedDate;
    private final String comment;
    private final List<OrderedProductDto> productsList;
}
