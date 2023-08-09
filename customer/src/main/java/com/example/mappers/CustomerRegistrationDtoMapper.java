package com.example.mappers;

import com.example.dto.CustomerRegistrationDto;
import com.example.model.Customer;

public class CustomerRegistrationDtoMapper {

    public static Customer toEntity(CustomerRegistrationDto dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }
}
