package com.project.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.header.HeaderWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class OauthResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    private static final String resourceId = "api-login";

    @Bean
    public TokenStore tokenStore() {

        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId).tokenStore(tokenStore());

        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**")
                .access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/**")
                .access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/**")
                .access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/**")
                .access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**")
                .access("#oauth2.hasScope('write')")
                .and()
                .headers()
                .addHeaderWriter(new HeaderWriter() {
                    @Override
                    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {

                        response.addHeader("Access-Control-Allow-Origin","*");
                        if (request.getMethod().equals(HttpMethod.OPTIONS)){
                            response.setHeader("Access-Control-Allow-Methods",
                                    request.getHeader("Access-Control-Request-Method"));
                            response.setHeader("Access-Control-Allow-Headers",
                                    request.getHeader("Access-Control-Request-Headers"));
                        }
                    }
                });

        super.configure(http);
    }
}
