package com.example;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class MailService {

    @RabbitListener(queues = "notificationQueue")
    public void processQueue1(String message) {
        System.out.println("Received from notification queue: " + message);
    }
}
