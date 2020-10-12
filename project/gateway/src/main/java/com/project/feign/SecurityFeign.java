package com.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "security-resources",fallback = DefaultFeignImp.class,configuration = DefaultFeignLog.class)
public interface SecurityFeign {

    @GetMapping("/sign/login")
    public String login();

    @GetMapping("/sign/verify/token")
    public String verifyToken(String token);
}
