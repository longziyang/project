//package com.project.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RouteConfig {
//
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
//
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//
//        RouteLocator routeLocator = routes.route("security-resources-route", r -> r.path("/**")
//                .uri("lb://security-resources").order(0)).build();
//
//        return routeLocator;
//    }
//}
