package com.project.security.password;

import com.project.security.exception.DefaultAuthException;
import com.project.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PasswordLoginFilter extends AbstractAuthenticationProcessingFilter {

    private boolean postOnly = true;
    private String usernameParameter = "username";
    private String passwordParameter = "password";

    protected PasswordLoginFilter() {
        // 传统登录的请求
        super(new AntPathRequestMatcher("/auth", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (postOnly && !request.getMethod().equals("POST")) {

            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String username = request.getParameter(usernameParameter);
        String password = request.getParameter(passwordParameter);

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {

            throw new DefaultAuthException("用户名或密码为空");
        }

        PasswordLoginToken passwordLoginToken = new PasswordLoginToken(username, password);
        setDetails(request, passwordLoginToken);

        return this.getAuthenticationManager().authenticate(passwordLoginToken);
    }

    private void setDetails(HttpServletRequest request, PasswordLoginToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
