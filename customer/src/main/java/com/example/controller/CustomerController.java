package com.example.controller;

import com.example.dto.CustomerRegistrationRequest;
import com.example.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public record CustomerController() {

    @PostMapping("")
    public void registrationUser(
            CustomerRegistrationRequest requestUser
    ) {
        Customer user = Customer.builder()
                .firstName(requestUser.getFirstName())
                .lastName(requestUser.getLastName())
                .email(requestUser.getEmail())
                .build();
    }
}
