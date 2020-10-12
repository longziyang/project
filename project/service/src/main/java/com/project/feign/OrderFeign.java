package com.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order-resources", fallback = DefaultFeignImp.class, configuration = DefaultLogger.class)
public interface OrderFeign {

    @GetMapping("/order/{id}")
    public String queryOrderInfo(@PathVariable Long id);
}
