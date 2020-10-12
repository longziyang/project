package com.project.security.oauth;

import com.project.entity.Role;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class OauthLoginToken extends AbstractAuthenticationToken {


    private String id;
    private String loginType;

    public OauthLoginToken(Collection<? extends GrantedAuthority> authorities, String id, String loginType) {
        super(authorities);
        this.id = id;
        this.loginType = loginType;
        super.setAuthenticated(true);
    }

    public String getLoginType() {
        return loginType;
    }

    @Override
    public Object getCredentials() {
        return id;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
