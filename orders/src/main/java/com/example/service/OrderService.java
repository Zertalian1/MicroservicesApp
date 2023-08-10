package com.example.service;

import com.example.dto.CreateOrderDto;
import com.example.dto.OrderFilterDto;
import com.example.dto.OrderInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<OrderInfoDto> getAllOrdersByFilter(Pageable pageable, OrderFilterDto filter);

    Page<OrderInfoDto> getAllOrders(Pageable pageable);

    Long addOrder(CreateOrderDto orderDto);

    OrderInfoDto getOrderById(Long orderId);
}
