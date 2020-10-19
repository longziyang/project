package com.project.feign;

public class UserFeignImp implements UserFeign{
    @Override
    public String transfer(String res) {
        return "网络超时";
    }
}
