package ru.darvell.gb.spring.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProxyConfig {
    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ui_route",
                        route -> route.path("/ui/**")
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://ui"))
                .route("shop_route",
                        route -> route.path("/shop/**")
                                .filters(filter -> filter.stripPrefix(1)
                                )
                                .uri("lb://shop"))
                .build();
    }
}
