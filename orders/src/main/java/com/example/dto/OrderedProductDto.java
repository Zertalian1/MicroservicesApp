package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderedProductDto {
    private Long product_id;
    private String name;
    private BigDecimal price;
}
