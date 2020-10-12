package com.project.security.password;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
public class PasswordLoginToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;
    private String loginType;

    public PasswordLoginToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(false);

        log.info("初始化密码验证token类");
    }

    public PasswordLoginToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials, String loginType) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.loginType = loginType;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
