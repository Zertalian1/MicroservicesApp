package com.example.messageQueue;

import com.example.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderProducer {

    /*@Value("${topic.name}")
    private String orderTopic;*/

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final NewTopic forwardClientsToOrderMicroserviceTopic;

    public KafkaOrderProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper,
            NewTopic forwardClientsToOrderMicroserviceTopic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.forwardClientsToOrderMicroserviceTopic = forwardClientsToOrderMicroserviceTopic;
    }

    public void sendMessage(Customer customer) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(customer);
        kafkaTemplate.send(forwardClientsToOrderMicroserviceTopic.name(), orderAsMessage);
    }
}
