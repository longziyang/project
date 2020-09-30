package com.project.security.email;

import com.project.security.exception.DefaultAuthException;
import com.project.common.Constant;
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

public class EmailLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationProvider authenticationProvider;

    private boolean postOnly = true;
    private String emailParameter = "email";
    private String codeParameter = "code";

    protected EmailLoginFilter() {
        // 传统登录的请求
        super(new AntPathRequestMatcher(Constant.LOGIN_PAGE, "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (postOnly && !request.getMethod().equals("POST")) {

            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String email = request.getParameter(emailParameter);
        String code = request.getParameter(codeParameter);

        if (StringUtils.isBlank(email) || StringUtils.isBlank(code)) {

            throw new DefaultAuthException("邮箱或验证码为空");
        }

        EmailLoginToken passwordLoginToken = new EmailLoginToken(email, code);
        setDetails(request, passwordLoginToken);

        return this.getAuthenticationManager().authenticate(passwordLoginToken);
    }

    private void setDetails(HttpServletRequest request, EmailLoginToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
