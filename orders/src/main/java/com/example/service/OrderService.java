package com.example.service;

import com.example.dto.CreateOrderDto;
import com.example.dto.OrderFilterDto;
import com.example.dto.OrderInfoDto;
import com.example.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderInfoDto> getAllOrdersByFilter(Pageable pageable, OrderFilterDto filter);

    Page<OrderInfoDto> getAllOrders(Pageable pageable);

    Order addOrder(CreateOrderDto orderDto);
}
