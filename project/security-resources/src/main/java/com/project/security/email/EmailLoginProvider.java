package com.project.security.email;

import com.project.entity.SysUser;
import com.project.security.exception.DefaultAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
public class EmailLoginProvider implements AuthenticationProvider {

    private EmailLoginUserDetailService passwordLoginUserDetailService;

    public EmailLoginProvider(EmailLoginUserDetailService passwordLoginUserDetailService) {

        this.passwordLoginUserDetailService = passwordLoginUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = (String) authentication.getPrincipal();
        String code = (String) authentication.getCredentials();

        SysUser user = (SysUser) passwordLoginUserDetailService.loadUserByUsername(email);
        if (!user.getCode().equals(code)){

            throw new DefaultAuthException("验证码错误");
        }

        authenticationChecks(user);

        EmailLoginToken authenticationResult = new EmailLoginToken(user.getAuthorities(), user.getEmail());
        authenticationResult.setDetails(user);

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailLoginProvider.class.isAssignableFrom(authentication);
    }

    private void authenticationChecks(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            log.debug("用户已被锁定");

            throw new LockedException("用户已被锁定");
        }

        if (!user.isEnabled()) {
            log.debug("用户已失效，请联系管理员");

            throw new DisabledException("用户已失效，请联系管理员");
        }

        if (!user.isAccountNonExpired()) {
            log.debug("用户已过期，请联系管理员");

            throw new AccountExpiredException("用户已过期，请联系管理员");
        }

        if (!user.isCredentialsNonExpired()) {
            log.debug("密码已过期");
            throw new CredentialsExpiredException("密码已过期");
        }
    }

}
