package com.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-resource",fallback = UserFeignImp.class)
public interface UserFeign {

    @RequestMapping("user/transfer")
    public String transfer(String res);
}
