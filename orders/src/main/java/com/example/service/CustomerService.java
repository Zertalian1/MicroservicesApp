package com.example.service;

import com.example.model.Customer;

import java.util.List;

public interface CustomerService {
    Long addCustomer(Customer customer);
    Customer getCustomerById(Long Id);
    List<Long> getCustomerIdListByFullName(String firstName, String lastName);
}
