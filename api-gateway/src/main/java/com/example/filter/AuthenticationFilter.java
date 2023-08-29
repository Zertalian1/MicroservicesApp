package com.example.filter;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private EurekaClient discoveryClient;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                final InstanceInfo instance = discoveryClient.getNextServerFromEureka("security-service", false);
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return redirectToAuth(instance, exchange);
                }
                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.noBody()
                            )
                            .timeout(Duration.of(2, SECONDS))
                            .uri(URI.create("http://localhost:8085"+"/security/validate?token="+authHeader))
                            .build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() != HttpStatus.OK.value()) {
                        return redirectToAuth(instance, exchange);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Filed to connect with auth service? try later");
                }
            }
            return chain.filter(exchange);
        });
    }

    private Mono<Void> redirectToAuth(InstanceInfo instance, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setLocation(URI.create(instance.getHomePageUrl()+"security/authenticate"));
        return response.setComplete();
    }

    public static class Config {

    }
}
