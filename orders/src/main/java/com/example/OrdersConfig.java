package com.example;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
@AllArgsConstructor
public class OrdersConfig {
    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }
}
