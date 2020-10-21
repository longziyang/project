//package com.project.oauth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.web.header.HeaderWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
//public class OauthResourcesConfig extends ResourceServerConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//   // @Value("${spring.stock.resourceId}")
//    private String resourceId = "api-stock";
//
//
//    /**
//     * 配置token存放位置
//     *
//     * @return
//     */
//    @Bean
//    public TokenStore tokenStore() {
//
//        return new JdbcTokenStore(dataSource);
//    }
//
//    public OauthResourcesConfig() {
//        super();
//    }
//
//
//    /**
//     * 当前系统的资源id 以及token存储方案
//     *
//     * @param resources
//     * @throws Exception
//     */
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//
//        resources
////                .tokenServices(new ResourceServerTokenServices() {
////                    @Override
////                    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
////                        return null;
////                    }
////
////                    @Override
////                    public OAuth2AccessToken readAccessToken(String accessToken) {
////                        return null;
////                    }
////                })
//                .resourceId(resourceId)
//                .tokenStore(tokenStore());
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**")
//                .access("#oauth2.hasScope('read')")
//                .antMatchers(HttpMethod.POST, "/**")
//                .access("#oauth2.hasScope('write')")
//                .antMatchers(HttpMethod.PATCH, "/**")
//                .access("#oauth2.hasScope('write')")
//                .antMatchers(HttpMethod.DELETE, "/**")
//                .access("#oauth2.hasScope('write')")
//                .antMatchers(HttpMethod.PUT, "/**")
//                .access("#oauth2.hasScope('write')")
//                .and()
//                .headers()
//                .addHeaderWriter(new HeaderWriter() {
//                    @Override
//                    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
//
//                        // 允许跨域
//                        response.addHeader("Access-Control-Allow-Origin", "*");
//                        if (request.getMethod().equals(HttpMethod.OPTIONS)) {
//// 如果是跨域的预检请求，则原封不动向下传达请求头信息
//                            response.setHeader("Access-Control-Allow-Methods",
//                                    request.getHeader("Access-Control-Request-Method"));
//                            response.setHeader("Access-Control-Allow-Headers",
//                                    request.getHeader("Access-Control-Request-Headers"));
//                        }
//                    }
//                });
//        super.configure(http);
//    }
//
//}
