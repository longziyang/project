package com.project;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.project.mapper")
public class ShardingJdbcApplication implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {

        SpringApplication.run(ShardingJdbcApplication.class);

        String[] str = applicationContext.getEnvironment()
                .getActiveProfiles();
        for (String to:str) {
            System.out.println("profiles = "+to);
        }
    }
}

