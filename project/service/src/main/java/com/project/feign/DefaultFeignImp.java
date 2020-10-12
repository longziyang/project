package com.project.feign;

import org.springframework.stereotype.Component;

@Component
public class DefaultFeignImp implements DefaultFeign {
    @Override
    public String queryOrderInfo(Long id) {

        return "网络错误";
    }
}
