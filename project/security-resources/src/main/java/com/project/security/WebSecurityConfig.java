package com.project.security;

import com.project.security.email.EmailLoginSecurityConfig;
import com.project.security.password.PasswordLoginSecurityConfig;
import com.project.security.password.PasswordLoginUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordLoginUserDetailService userDetailService;
    @Autowired
    private EmailLoginSecurityConfig emailLoginSecurityConfig;
    @Autowired
    private PasswordLoginSecurityConfig passwordLoginSecurityConfig;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        // 设置数据源
        tokenRepository.setDataSource(dataSource);
        // 启动的时候创建存储token的表
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 设置拦截忽略文件夹，可以对静态资源放行
//        web.ignoring().antMatchers("/assets/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TokenVerifyFilter tokenVerifyFilter = new TokenVerifyFilter(authenticationManagerBean());
        http
                .apply(passwordLoginSecurityConfig).and()
                .apply(emailLoginSecurityConfig).and()
                .authorizeRequests()
                    .antMatchers("/sign/*", "/ico/**", "/css/**", "/js/**", "/img/**").permitAll()
                    .antMatchers().hasAnyRole("USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/sign/loginPage")
                    //.loginProcessingUrl("/sign/loginUser")
                    //.defaultSuccessUrl("/sign/index", true)
                    .failureUrl("/sign/loginError")
                    .permitAll().and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/sign/loginPage")
                    .invalidateHttpSession(true)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(66);


    }

}