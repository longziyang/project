package com.project.security.email;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class EmailLoginToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;
    private String loginType;

    public EmailLoginToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(false);
    }

    public EmailLoginToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public EmailLoginToken(Collection<? extends GrantedAuthority> authorities, Object principal, String loginType) {
        super(authorities);
        this.principal = principal;
        this.loginType = loginType;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
