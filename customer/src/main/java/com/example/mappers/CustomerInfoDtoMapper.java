package com.example.mappers;

import com.example.dto.CustomerInfoDto;
import com.example.model.Customer;

public class CustomerInfoDtoMapper {

    public static CustomerInfoDto toDto(Customer entity) {
        return CustomerInfoDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
