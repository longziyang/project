package com.project.security.oauth;

import com.project.entity.Role;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

public class OauthLoginToken extends AbstractAuthenticationToken {


    private Long id;
    private String loginType;

    public OauthLoginToken(List<Role> authorities, Long id, String loginType) {
        super(null);
        this.id = id;
        this.loginType = loginType;
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
