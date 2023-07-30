package com.example.service;

import com.example.dto.CustomerRegistrationRequest;
import com.example.mappers.CustomerRegistrationRequestMapper;
import com.example.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    public int addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        return customerRepository.addCustomer(CustomerRegistrationRequestMapper.toEntity(customerRegistrationRequest));
    }


}
