package com.example.mappers;

import com.example.dto.CustomerRegistrationRequest;
import com.example.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerRegistrationRequestMapper{

    public static Customer toEntity(CustomerRegistrationRequest customerRegistrationRequest) {
        return Customer.builder()
                .firstName(customerRegistrationRequest.getFirstName())
                .lastName(customerRegistrationRequest.getLastName())
                .email(customerRegistrationRequest.getEmail())
                .build();
    }
}
