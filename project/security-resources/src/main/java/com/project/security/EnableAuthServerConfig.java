//package com.project.security;
//
//import com.project.security.password.PasswordLoginUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableAuthorizationServer
//public class EnableAuthServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private PasswordLoginUserDetailService userDetailsService;
//    @Autowired
//    private JdbcClientDetailsService jdbcClientDetailsService;
//    //密码模式专用
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Bean
//    public JdbcClientDetailsService jdbcClientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//
//    public EnableAuthServerConfig() {
//        super();
//    }
//
//    /**
//     * 授权信息保存策略
//     * @return
//     */
//    @Bean
//    public ApprovalStore approvalStore(){
//        return new JdbcApprovalStore(dataSource);
//    }
//
//    /**
//     * token保存策略
//     * @return
//     */
//    @Bean
//    public TokenStore tokenStore() {
//
//        return new JdbcTokenStore(dataSource);
//    }
//
//    /**
//     * 授权码模式专用
//     * @return
//     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(){
//
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }
//
//    /**
//     * 检查token的策略
//     * @param security
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients()
//                .checkTokenAccess("isAuthenticated()");
//        super.configure(security);
//    }
//
//    /**
//     * 指定客户端来源
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//        clients.withClientDetails(jdbcClientDetailsService);
//        super.configure(clients);
//    }
//
//    /**
//     * OAuth2的主配置信息
//     * .pathMapping() 第一个参数是自定义的参数 第二个是需要替换的参数
//     * /oauth/authorize：授权端点。
//     * /oauth/token：令牌端点。
//     * /oauth/confirm_access：用户确认授权提交端点。
//     * /oauth/error：授权服务错误信息端点。
//     * /oauth/check_token：用于资源服务访问的令牌解析端点。
//     * /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。
//     *
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//
//        endpoints
//                .authenticationManager(authenticationManager)
//                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
//                .userDetailsService(userDetailsService)
//                .approvalStore(approvalStore())
//                .authorizationCodeServices(authorizationCodeServices())
//                .tokenStore(tokenStore());
//        super.configure(endpoints);
//    }
//}
