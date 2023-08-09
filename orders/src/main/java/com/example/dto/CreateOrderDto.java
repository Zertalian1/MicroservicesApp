package com.example.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {
    private String comment;
    private Long customerId;
    private List<Long> productsIdList = new ArrayList<>();
}
