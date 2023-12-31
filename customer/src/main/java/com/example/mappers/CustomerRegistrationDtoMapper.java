package com.example.mappers;

import com.example.dto.CustomerRegistrationDto;
import com.example.model.Customer;

public class CustomerRegistrationDtoMapper {

    public static Customer toEntity(CustomerRegistrationDto dto) {
        return Customer.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .build();
    }
}
