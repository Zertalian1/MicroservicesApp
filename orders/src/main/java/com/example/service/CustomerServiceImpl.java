package com.example.service;

import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CustomerServiceImpl(
        CustomerRepository customerRepository
) implements CustomerService{
    @Override
    public Long addCustomer(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    @Override
    public Customer getCustomerById(Long Id) {
        return customerRepository.findById(Id).orElseThrow(() -> new RuntimeException("User not exists"));
    }

    @Override
    public List<Long> getCustomerIdListByFullName(String firstName, String lastName) {
        List<Customer> customerList = customerRepository.findCustomerByFirstNameIgnoreCaseLikeAndLastNameIgnoreCaseLike(
                firstName,
                lastName
        );
        return customerList.stream().map(Customer::getId).toList();
    }
}
