package com.project.web;

/**
 *
 * @author ysh
 * create by 2020-10-6 22:34:15
 * 
 */


import com.alibaba.fastjson.JSON;
import com.project.config.SignedConfig;
import com.project.entity.SysUser;
import com.project.common.LoginUserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/home")
    public String home(Model model, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = (SysUser) authentication.getDetails();
        //SysUser user = (SysUser) authentication.getPrincipal();
        String username = user.getUsername();
        String key = SignedConfig.todayKey;
        Object object = redisTemplate.opsForHash().get(key, username);

        // 今天第一次登录
        if (object == null) {

            String val = JSON.toJSONString(user);
            model.addAttribute("sign", true);
            redisTemplate.opsForHash().put(key, username, val);
        }


        return "sign/home";
    }

    @RequestMapping("/")
    public String index() {

        return "sign/home";
    }

    @GetMapping("/403")
    public String accessDenied() {

        System.out.println("403页面");
        return "exception/403";
    }

}
