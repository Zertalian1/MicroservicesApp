package com.example.controller;

import com.example.dto.CreateOrderDto;
import com.example.dto.OrderFilterDto;
import com.example.dto.OrderInfoDto;
import com.example.model.Customer;
import com.example.service.CustomerService;
import com.example.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public record OrderController(
    OrderService orderService,
    CustomerService customerService
) {

    @PostMapping(value = "/add")
    public Long addNewOrder(
            @RequestBody CreateOrderDto order
    ) {
        return orderService.addOrder(order);
    }

    @PostMapping(value = "/completed")
    public HttpStatus markOrderCompleted(
    ) {
        return HttpStatus.OK;
    }

    @PostMapping(value = "/add-customer")
    public Long addNewCustomer(
            @RequestBody Customer customer
    ) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("")
    public Page<OrderInfoDto> getAllOrders(
            @PageableDefault Pageable pageable
    ) {
        return orderService.getAllOrders(pageable);
    }

    @GetMapping("/{orderId}")
    public OrderInfoDto getOrderById(
            @PathVariable Long orderId
    ) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping(value = "", headers = "filter=true")
    public Page<OrderInfoDto> getAllOrdersByFilter(
            @PageableDefault Pageable pageable,
            @RequestBody OrderFilterDto filter
    ) {
        return orderService.getAllOrdersByFilter(pageable, filter);
    }
}
