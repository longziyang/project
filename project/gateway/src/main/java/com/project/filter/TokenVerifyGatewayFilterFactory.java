package com.project.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;

@Component
public class TokenVerifyGatewayFilterFactory implements GlobalFilter , Ordered {

    @Value("${token.invalid.ignored}")
    private String ignored;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request=exchange.getRequest();
        String url = request.getPath().value();
        //if (StringUtils.compareT(url,ignored))
        return null;
    }

    @NotNull
    public static void main(String[] args) {


    }


    @Override
    public int getOrder() {
        return 0;
    }
}
