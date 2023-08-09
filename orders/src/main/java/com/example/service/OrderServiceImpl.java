package com.example.service;

import com.example.dto.CustomerFilterDto;
import com.example.dto.OrderFilterDto;
import com.example.dto.OrderInfoDto;
import com.example.mappers.OrderInfoDtoMapper;
import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public record OrderServiceImpl(
        OrderRepository orderRepository,
        ObjectMapper objectMapper,
        HttpClient httpClient
) implements OrderService {

    @Override
    public Page<OrderInfoDto> getAllOrdersByFilter(Pageable pageable, OrderFilterDto filter) {
        CustomerFilterDto customerFilter = new CustomerFilterDto(
                filter.getCustomerFirstName(),
                filter.getCustomerLastName()
        );
        List<Long> customersIdList;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(
                            HttpRequest.BodyPublishers.ofString(
                                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerFilter)
                            )
                    )
                    .header("filter", "true")
                    .header("Content-Type", "application/json")
                    .timeout(Duration.of(2, SECONDS))
                    .uri(URI.create("http://localhost:8080/customers/get-all-id"))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            customersIdList = List.of(objectMapper.readValue(response.body(), Long[].class));
        } catch (Exception e) {
            throw new RuntimeException("filed to get clients");
        }
        Page<Order> orderList = orderRepository.getOrdersByCustomerIdIn(customersIdList, pageable);
        return orderList.map(OrderInfoDtoMapper::toDto);
    }

    @Override
    public Page<OrderInfoDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderInfoDtoMapper::toDto);
    }
}
