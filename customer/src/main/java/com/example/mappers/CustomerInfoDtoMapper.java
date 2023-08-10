package com.example.mappers;

import com.example.dto.CustomerInfoDto;
import com.example.model.Customer;

public class CustomerInfoDtoMapper {

    public static CustomerInfoDto toDto(Customer entity) {
        return new CustomerInfoDto(entity.getId(), entity.getFirstName(), entity.getLastName());
    }
}
