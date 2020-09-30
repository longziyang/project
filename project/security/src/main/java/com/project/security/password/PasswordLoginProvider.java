package com.project.security.password;

import com.project.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordLoginProvider implements AuthenticationProvider {

    private PasswordLoginUserDetailService passwordLoginUserDetailService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordLoginProvider(PasswordLoginUserDetailService passwordLoginUserDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordLoginUserDetailService = passwordLoginUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        SysUser user = (SysUser) passwordLoginUserDetailService.loadUserByUsername(username);


        if (user == null || !bCryptPasswordEncoder.matches(password, user.getPassword())) {

            throw new BadCredentialsException("密码错误");
        }

        authenticationChecks(user);

        PasswordLoginToken authenticationResult = new PasswordLoginToken(user.getAuthorities(), user.getUsername(), user.getPassword());
        authenticationResult.setDetails(user);

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PasswordLoginProvider.class.isAssignableFrom(authentication);
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
