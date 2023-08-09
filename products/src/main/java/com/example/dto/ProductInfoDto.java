package com.example.dto;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ProductInfoDto {
    private String name;
    private String description;
    private BigDecimal price;
}
