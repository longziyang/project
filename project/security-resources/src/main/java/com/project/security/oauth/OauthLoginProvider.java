package com.project.security.oauth;

import com.project.common.enums.LoginTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class OauthLoginProvider  implements AuthenticationProvider {

    private OauthLoginUserDetailService oauthLoginUserDetailService;

    public void setOauthLoginUserDetailService(OauthLoginUserDetailService oauthLoginUserDetailService) {
        this.oauthLoginUserDetailService = oauthLoginUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = (String) authentication.getPrincipal();
        UserDetails userDetails = oauthLoginUserDetailService.loadUserByUsername(userId);
        OauthLoginToken oauthLoginToken = new OauthLoginToken(userDetails.getAuthorities(),userId, LoginTypeEnum.EMAIL.detail);

        authenticationChecks(userDetails);
        oauthLoginToken.setDetails(userDetails);

        return oauthLoginToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OauthLoginToken.class.isAssignableFrom(authentication);
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
