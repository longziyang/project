package com.project.filter;

import com.project.commom.Constant;
import com.project.feign.DefaultFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TokenVerifyGatewayFilterFactory implements GlobalFilter, Ordered {

    @Qualifier("defaultFeignImp")
    @Autowired
    private DefaultFeign defaultFeign;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("权限检查  全局过滤器");
        try {
            ServerHttpRequest request = exchange.getRequest();
            String url = request.getPath().value();
            if (url.startsWith(Constant.IGNORE_URL)||url.startsWith(Constant.IGNORE_CSS)) {

                return chain.filter(exchange);

            } else {

                //获取授权信息
                String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (StringUtils.isBlank(authentication)) {

                    ServerHttpResponse response = exchange.getResponse();

                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return hasException(exchange, Constant.UNAUTHORIZED);
                }

                //远程调用 验证token是否合法
                String result = defaultFeign.verifyToken(authentication);
                if (Constant.TOKEN_IS_AUTHENTICATION.equals(result))

                //token不合法或已过期  提示没有权限
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        } catch (Exception e) {

            return hasException(exchange, Constant.SERVICE_UNAVAILABLE);
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 无权限错误提示
     *
     * @param serverWebExchange
     * @return
     */
    private Mono<Void> hasException(ServerWebExchange serverWebExchange, String msg) {

        //设置返回码
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        //返回信息
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(msg.getBytes());
        //返回
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

}
