package com.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class QQLoginController {

    @RequestMapping("/login/qq")
    public String login(HttpServletRequest request, HttpServletResponse response){

        request.getParameterMap();
        return "";
    }
}
