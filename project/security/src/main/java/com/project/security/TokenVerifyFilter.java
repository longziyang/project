package com.project.security;

import com.project.config.KeyConfig;
import com.project.entity.SysUser;
import com.project.security.email.EmailLoginToken;
import com.project.security.exception.DefaultAuthException;
import com.project.security.oauth.OauthLoginToken;
import com.project.security.password.PasswordLoginToken;
import com.project.util.jwt.JwtUtils;
import com.project.util.jwt.Payload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.common.enums.LoginTypeEnum.*;

public class TokenVerifyFilter extends BasicAuthenticationFilter {

    private KeyConfig config;

    public TokenVerifyFilter(AuthenticationManager authenticationManager, KeyConfig config) {
        super(authenticationManager);
        this.config = config;
        System.out.println("加载token验证过滤器");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader("Authorization");
        if (StringUtils.isBlank(header) && !header.startsWith("Bearer")) {

            chain.doFilter(request, response);
            // 未登录提示
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        AbstractAuthenticationToken userToken = getAuthentication(header.replace("Bearer", ""));
        SecurityContextHolder.getContext().setAuthentication(userToken);
        chain.doFilter(request, response);

        super.doFilterInternal(request, response, chain);
    }

    /**
     * 通过token，获取用户信息
     *
     * @return
     */
    private AbstractAuthenticationToken getAuthentication(String token) {

        System.out.println("根据token获取用户信息");

        Payload<SysUser> payload = JwtUtils.getInfoFromToken(token, config.getPublicKey(), SysUser.class);
        SysUser user = payload.getUserInfo();
        if (null != user) {

            String loginType = payload.getLoginType();
            if (loginType.equals(EMAIL.detail)) {

                return new EmailLoginToken(user.getAuthorities(), user.getEmail(), EMAIL.detail);
            } else if (loginType.equals(PASSWORD.detail)) {

                return new PasswordLoginToken(user.getAuthorities(), user.getUsername(), null, PASSWORD.detail);
            } else if (loginType.equals(OAUTH.detail)) {

                return new OauthLoginToken(user.getAuthorities(), user.getId(), OAUTH.detail);
            } else if (loginType.equals(SMS.detail)) {

                return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            }
        }

        throw new DefaultAuthException("用户信息异常 请重新登陆");
    }
}
