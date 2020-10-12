package com.project.security.handler;

import com.project.common.LoginUserInfo;
import com.project.config.KeyConfig;
import com.project.entity.Role;
import com.project.entity.SysUser;
import com.project.util.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class SuccessAuthHandler implements AuthenticationSuccessHandler {

    @Autowired
    private KeyConfig keyConfig;
    private static final String INDEX_URL="/home";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.debug("用户登陆成功 生成token");
        SysUser user = new SysUser();
        user.setUsername(authentication.getName());
        user.setAuthorities((List<Role>) authentication.getAuthorities());

        String token = JwtUtils.generateTokenExpireInSeconds(user, keyConfig.getPrivateKey(), 60 * 1000 * 10);
        response.setHeader("Authorization","Bearer"+token);
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        LoginUserInfo.createUser(user);
        response.sendRedirect(INDEX_URL);
    }
}
