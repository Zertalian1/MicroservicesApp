package com.example.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Builder
@Data
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
