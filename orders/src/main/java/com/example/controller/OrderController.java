package com.example.controller;

import com.example.dto.OrderFilterDto;
import com.example.dto.OrderInfoDto;
import com.example.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public record OrderController(
    OrderService orderService
) {

    @GetMapping("")
    public Page<OrderInfoDto> getAllOrders(
            @PageableDefault Pageable pageable
    ) {
        return orderService.getAllOrders(pageable);
    }

    @PostMapping(value = "", headers = "filter=true")
    public Page<OrderInfoDto> getAllOrdersByFilter(
            @PageableDefault Pageable pageable,
            @RequestBody OrderFilterDto filter
    ) {
        return orderService.getAllOrdersByFilter(pageable, filter);
    }
}
