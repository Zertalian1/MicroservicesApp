package com.example.controller;

import com.example.dto.CustomerFilterDto;
import com.example.dto.CustomerInfoDto;
import com.example.dto.CustomerRegistrationDto;
import com.example.model.Customer;
import com.example.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping("/add")
    public Long registrationUser(@RequestBody CustomerRegistrationDto customerRegistrationDto) {
        return customerService.addCustomer(customerRegistrationDto);
    }

    @GetMapping("/test")
    public Customer getAllUsers(

    ) {
        return new Customer(0L, "test", "test", "test");
    }

    @GetMapping("/get-all")
    public Page<CustomerInfoDto> getAllUsers(@PageableDefault Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @PostMapping(value = "/get-all-id", headers = "filter=true")
    public List<Long> getAllUsersIdByNames(
            @RequestBody CustomerFilterDto filter
    ) {
        System.out.println("new request");
        System.out.println(filter.toString());
        return customerService.getAllCustomersIdByFilter(filter);
    }
}
