package com.example.service;

import com.example.dto.CustomerFilterDto;
import com.example.dto.CustomerInfoDto;
import com.example.dto.CustomerRegistrationDto;
import com.example.mappers.CustomerInfoDtoMapper;
import com.example.mappers.CustomerRegistrationDtoMapper;
import com.example.messageQueue.OrderProducer;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import com.example.repository.CustomerRepositoryJdbcTemplates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public final class CustomerServiceImpl implements CustomerService {
    private final CustomerRepositoryJdbcTemplates customerRepositoryJdbcTemplates;
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;
    private final OrderProducer orderProducer;

    @Override
    public Long addCustomer(CustomerRegistrationDto customerRegistrationDto) {
        Customer savedCustomer = customerRepository.save(CustomerRegistrationDtoMapper.toEntity(customerRegistrationDto));
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(
                            HttpRequest.BodyPublishers.ofString(
                                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(savedCustomer)
                            )
                    )
                    .header("Content-Type", "application/json")
                    .timeout(Duration.of(2, SECONDS))
                    .uri(URI.create("http://localhost:8081/orders/add-customer"))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200){
                customerRepository.deleteById(savedCustomer.getId());
                throw new RuntimeException("filed to save client");
            }
        } catch (Exception e) {
            try {
                orderProducer.sendMessage(savedCustomer);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException("filed to add client to queue");
            }
            throw new RuntimeException("filed to forward client");
        }
        return savedCustomer.getId();
    }

    @Override
    public Page<CustomerInfoDto> getAllCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(CustomerInfoDtoMapper::toDto);
    }

    @Override
    public List<Long> getAllCustomersIdByFilter(CustomerFilterDto filter) {
        return customerRepositoryJdbcTemplates.getAllCustomersIdsByFilter(filter);
    }

}
