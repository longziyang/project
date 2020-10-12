package com.project.feign;

import com.project.commom.Constant;
import org.springframework.stereotype.Component;

@Component
public class DefaultFeignImp implements DefaultFeign {

    @Override
    public String login() {
        return Constant.BUSY_NETWORK;
    }

    @Override
    public String verifyToken(String token) {
        return Constant.BUSY_NETWORK;
    }
}
