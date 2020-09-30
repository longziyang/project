package com.project.feign;

import com.project.common.Constant;
import com.project.entity.SysUser;
import org.springframework.stereotype.Component;

@Component
public class FeignTemplateImp implements FeignTemplate {

    @Override
    public String selectUserBy(Object key, String loginType) {

        return Constant.BUSY_NETWORK;
    }
}
