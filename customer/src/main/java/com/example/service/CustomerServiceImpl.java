package com.example.service;

import com.example.dto.CustomerInfoDto;
import com.example.dto.CustomerRegistrationDto;
import com.example.mappers.CustomerInfoDtoMapper;
import com.example.mappers.CustomerRegistrationDtoMapper;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import com.example.repository.CustomerRepositoryJdbcTemplates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public record CustomerServiceImpl(
        CustomerRepositoryJdbcTemplates customerRepositoryJdbcTemplates,
        CustomerRepository customerRepository
) implements CustomerService {


    @Override
    public int addCustomer(CustomerRegistrationDto customerRegistrationDto) {
        return customerRepositoryJdbcTemplates.addCustomer(CustomerRegistrationDtoMapper.toEntity(customerRegistrationDto));
    }

    @Override
    public Page<CustomerInfoDto> getAllCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(CustomerInfoDtoMapper::toDto);
    }


}
