package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerFilterDto {
    private final String firstName;
    private final String lastName;
}
