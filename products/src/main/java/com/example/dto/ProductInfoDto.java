package com.example.dto;

import java.math.BigDecimal;

public record ProductInfoDto (
    String name,
    String description,
    BigDecimal price
) {

}
