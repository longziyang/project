package com.project.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Denied是拒签的意思 此处我们可以自定义403响应的内容,让他返回我们的错误逻辑提示
 */
public class AccessDeniedHandlerImp implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        RespBean error = RespBean.error("权限不足，请联系管理员!");
//        out.write(new ObjectMapper().writeValueAsString(error));
        out.write("权限不足，请联系管理员!");
        out.flush();
        out.close();
    }
}
