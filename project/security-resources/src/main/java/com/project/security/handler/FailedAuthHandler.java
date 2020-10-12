package com.project.security.handler;

import com.project.common.Constant;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class FailedAuthHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.sendRedirect(Constant.LOGIN_PAGE);

//        response.setContentType("application/json;charset=utf-8");
//        String text;
//        PrintWriter out = response.getWriter();
//        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
//            text = "账户名或者密码输入错误!";
//        } else if (exception instanceof LockedException) {
//            text = "账户被锁定，请联系管理员!";
//        } else if (exception instanceof CredentialsExpiredException) {
//            text = "密码过期，请联系管理员!";
//        } else if (exception instanceof AccountExpiredException) {
//            text = "账户过期，请联系管理员!";
//        } else if (exception instanceof DisabledException) {
//            text = "账户被禁用，请联系管理员!";
//        } else {
//            text = "登录失败!";
//        }
//        response.setStatus(401);
//        out.write(text);
//        out.flush();
//        out.close();
    }
}
