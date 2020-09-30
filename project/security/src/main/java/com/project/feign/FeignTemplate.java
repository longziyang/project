package com.project.feign;

import com.project.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "login-resources", fallback = FeignTemplateImp.class, configuration = FeignLog.class)
public interface FeignTemplate {

    public String selectUserBy(Object key, String loginType);
}
