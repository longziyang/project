package com.project.web;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@DefaultProperties(defaultFallback = "defaultBack")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/order/{id}")
    //@HystrixCommand(fallbackMethod = "fallbackMethod")
    @HystrixCommand
    public String queryOrderInfo(@PathVariable Long id) {

//        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("order-resources");
//        int port = serviceInstanceList.get(0).getPort();
//        String host = serviceInstanceList.get(0).getHost();
//        String url = "http://" + host + ":" + port + "/order/" + id;
//        String url = "http://order-resources/order/" + id;

        return orderFeign.queryOrderInfo(id);
        //return restTemplate.getForObject(url, String.class);
    }

    public String defaultBack() {
        return "网络超时";
    }
}
