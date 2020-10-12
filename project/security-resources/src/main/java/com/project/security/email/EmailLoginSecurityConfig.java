package com.project.security.email;

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
public class EmailLoginSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private EmailLoginUserDetailService passwordLoginUserDetailService;
    @Autowired
    private SuccessAuthHandler successAuthHandler;
    @Autowired
    private FailedAuthHandler failedAuthHandler;

    @Override
    public void configure(HttpSecurity builder) throws Exception {

        EmailLoginFilter passwordLoginFilter = new EmailLoginFilter();

        passwordLoginFilter.setAuthenticationSuccessHandler(successAuthHandler);
        passwordLoginFilter.setAuthenticationFailureHandler(failedAuthHandler);

        EmailLoginProvider passwordLoginProvider = new EmailLoginProvider(passwordLoginUserDetailService);
        builder
                .authenticationProvider(passwordLoginProvider)
                .addFilterAfter(passwordLoginFilter, UsernamePasswordAuthenticationFilter.class);
        super.configure(builder);

    }
}
