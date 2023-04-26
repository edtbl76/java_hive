package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration configuration) {
        String httpUri = configuration.getHttpbin();
        return builder.routes()
                .route(predicateSpec -> predicateSpec
                        .path("/get")
                        .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.addRequestHeader("Hello", "World"))
                        .uri(httpUri))
                .route(predicateSpec -> predicateSpec
                        .host("*.circuitbreaker.com")
                        .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.circuitBreaker(config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback")))
                        .uri(httpUri))
                .build();
    }
}
