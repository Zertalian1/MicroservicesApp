package com.example.messageQueue;

import com.example.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    @Value("${topic.name}")
    private String orderTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(Customer customer) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(customer);
        kafkaTemplate.send(orderTopic, orderAsMessage);
    }
}
