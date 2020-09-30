//package com.project.filter;
//
//import java.io.UnsupportedEncodingException;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//import com.lxtech.comm.entity.mic.CompositeData;
//import com.lxtech.comm.util.JacksonUtil;
//import com.lxtech.server.gateway.service.IUserAuthService;
//
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//
///**
// * token验证拦截
// * @author Aries.ai
// *
// */
//@Component
//public class AccessGatewayFilter implements GlobalFilter, Ordered  {
//
//	private static Logger logger = LoggerFactory.getLogger(AccessGatewayFilter.class);
//
//	private static String GATEWAY_INFO = "getway-info";
//
//	@Autowired
//	private IUserAuthService uacService;
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        ServerHttpRequest request = exchange.getRequest();
//        //获取授权信息
//        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        String method = request.getMethodValue();
//        String url = request.getPath().value();
//
//        if(logger.isDebugEnabled()) {
//        	logger.debug("url:{},method:{},headers:{}, authentication:{}", url, method, request.getHeaders(), authentication);
//        }
//
//        //需要忽略的URL
//        if (uacService.ignoreAuthentication(url)) {
//            return chain.filter(exchange);
//        }
//
//        //非忽略URL，必须携带token
//        if(StringUtils.isEmpty(authentication)) {
//        	String msg = "Header Authorization does not exist and requires a valid token";
//        	return unauthorized(exchange, msg);
//        }
//
//        //调用远程认证服务，校验是否授权
//        if (uacService.hasPermission(authentication, url, method)) {
//
//            ServerHttpRequest.Builder builder = request.mutate();
//            //TODO 转发的请求都加上服务间认证token
//            builder.header(GATEWAY_INFO, "Gateway authorization transfer", "");
//
//            return chain.filter(exchange.mutate().request(builder.build()).build());
//        }
//
//        return unauthorized(exchange, null);
//	}
//
//
//	/**
//	 * 无权限错误提示
//	 * @param serverWebExchange
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 */
//    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange, String msg){
//
//    	// 组装返回报文
//    	CompositeData<String> cd = new CompositeData<>();
//
//    	String errMsg = "No permission to access the URL, the request was denied";
//
//    	if(StringUtils.isNotEmpty(msg)) {
//    		errMsg = msg;
//    	}
//    	//设置错误码
//    	cd.setReturnData("100401", errMsg, CompositeData.ERROR_STATUS);
//    	//转换json数据
//    	String message = JacksonUtil.toJSon(cd);
//    	//设置返回码
//        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
//        //返回信息
//        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(message.getBytes());
//        //返回
//        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
//    }
//
//
//	@Override
//	public int getOrder() {
//		return 0;
//	}
//
//}
