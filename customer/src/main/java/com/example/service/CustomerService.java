package com.example.service;

import com.example.dto.CustomerInfoDto;
import com.example.dto.CustomerRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    int addCustomer(CustomerRegistrationDto customerRegistrationDto);

    Page<CustomerInfoDto> getAllCustomers(Pageable pageable);

}
