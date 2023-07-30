package com.example.controller;

import com.example.dto.CustomerRegistrationRequest;
import com.example.model.Customer;
import com.example.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public record CustomerController(
        CustomerService customerService
) {

    @GetMapping("")
    public Customer getPrepareUser() {
        return Customer.builder()
                .firstName("Данил")
                .lastName("Курдюков")
                .email("testEmail@mail")
                .build();
    }

    @PostMapping("/registration")
    public int registrationUser(CustomerRegistrationRequest customerRegistrationRequest) {
        return customerService.addCustomer(customerRegistrationRequest);
    }


}
