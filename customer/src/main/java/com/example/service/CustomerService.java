package com.example.service;

import com.example.dto.CustomerFilterDto;
import com.example.dto.CustomerInfoDto;
import com.example.dto.CustomerRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Long addCustomer(CustomerRegistrationDto customerRegistrationDto);

    Page<CustomerInfoDto> getAllCustomers(Pageable pageable);

    List<Long> getAllCustomersIdByFilter(CustomerFilterDto filter);

}
