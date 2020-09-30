package com.project.security.password;

import com.project.security.handler.FailedAuthHandler;
import com.project.security.handler.SuccessAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class PasswordLoginSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PasswordLoginUserDetailService passwordLoginUserDetailService;
    @Autowired
    private SuccessAuthHandler successAuthHandler;
    @Autowired
    private FailedAuthHandler failedAuthHandler;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(HttpSecurity builder) throws Exception {

        PasswordLoginFilter passwordLoginFilter = new PasswordLoginFilter();

        passwordLoginFilter.setAuthenticationManager(authenticationManager);
        passwordLoginFilter.setAuthenticationSuccessHandler(successAuthHandler);
        passwordLoginFilter.setAuthenticationFailureHandler(failedAuthHandler);

        PasswordLoginProvider passwordLoginProvider = new PasswordLoginProvider(passwordLoginUserDetailService, bCryptPasswordEncoder);
        builder
                .authenticationProvider(passwordLoginProvider)
                .addFilterAfter(passwordLoginFilter, UsernamePasswordAuthenticationFilter.class);
        super.configure(builder);

    }
}
