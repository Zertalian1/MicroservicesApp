package com.example.messageQueue;

import com.example.model.Customer;
import com.example.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private static final String orderTopic = "${topic.name}";

    private final ObjectMapper objectMapper;
    private final CustomerService customerService;

    public Consumer(ObjectMapper objectMapper, CustomerService customerService) {
        this.objectMapper = objectMapper;
        this.customerService = customerService;
    }

    @KafkaListener(topics = orderTopic)
    public void consumeMessage(String message) throws JsonProcessingException {
        Customer customer = objectMapper.readValue(message, Customer.class);
        customerService.addCustomer(customer);
    }
}
