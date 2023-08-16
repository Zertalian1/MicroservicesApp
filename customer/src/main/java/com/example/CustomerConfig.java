package com.example;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class CustomerConfig {

    @Bean
    @LoadBalanced
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }
}
