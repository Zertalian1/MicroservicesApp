package com.example.service;

import com.example.dto.CreateOrderDto;
import com.example.dto.OrderFilterDto;
import com.example.dto.OrderInfoDto;
import com.example.mappers.OrderInfoDtoMapper;
import com.example.model.Order;
import com.example.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public record OrderServiceImpl(
        OrderRepository orderRepository,
        CustomerService customerService,
        ProductService productService
) implements OrderService {

    @Override
    public Page<OrderInfoDto> getAllOrdersByFilter(Pageable pageable, OrderFilterDto filter) {
        Page<Order> orderList = orderRepository.getOrdersByCustomerIdIn(
                customerService.getCustomerIdListByFullName(
                        filter.getCustomerFirstName(),
                        filter.getCustomerLastName()
                ),
                pageable
        );
        return orderList.map(OrderInfoDtoMapper::toDto);
    }

    @Override
    public Page<OrderInfoDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderInfoDtoMapper::toDto);
    }

    @Override
    public Order addOrder(CreateOrderDto orderDto) {
        Order newOrder = new Order();
        newOrder.setComment(orderDto.getComment());
        newOrder.setCustomer(customerService.getCustomerById(orderDto.getCustomerId()));
        newOrder.getProductsList().addAll(productService.createOrderedProductByProductId(orderDto.getProductsIdList()));
        return orderRepository.save(newOrder);
    }
}
