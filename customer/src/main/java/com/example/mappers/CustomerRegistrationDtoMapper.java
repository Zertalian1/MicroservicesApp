package com.example.mappers;

import com.example.dto.CustomerRegistrationDto;
import com.example.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerRegistrationDtoMapper {

    public static Customer toEntity(CustomerRegistrationDto dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }
}
