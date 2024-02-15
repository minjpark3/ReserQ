package com.example.mo_api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/api/users/**")
                        .uri("http://localhost:8080/"))
                .route(r -> r.path("/api/posts/**")
                        .uri("http://localhost:8081/"))
                .route(r -> r.path("/api/comment/**")
                        .uri("http://localhost:8081/"))
                .route(r -> r.path("/api/follower/**")
                        .uri("http://localhost:8081"))
                .route(r-> r.path("/api/newsfeed/**")
                        .uri("http://localhost:8082"))
                .build();

    }
}