package com.project.security.password;

import com.project.security.handler.FailedAuthHandler;
import com.project.security.handler.SuccessAuthHandler;
import lombok.extern.slf4j.Slf4j;
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
    private PasswordLoginUserDetailService userDetailService;
    @Autowired
    private SuccessAuthHandler successAuthHandler;
    @Autowired
    private FailedAuthHandler failedAuthHandler;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void configure(HttpSecurity builder) throws Exception {

        PasswordLoginFilter filter = new PasswordLoginFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(successAuthHandler);
        filter.setAuthenticationFailureHandler(failedAuthHandler);

        PasswordLoginProvider provider = new PasswordLoginProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setbCryptPasswordEncoder(bCryptPasswordEncoder);

        builder.authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
        super.configure(builder);
    }
}
