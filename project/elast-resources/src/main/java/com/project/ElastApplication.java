package com.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@EnableEurekaClient
@MapperScan(basePackages = "com.my")
public class ElastApplication {

    public static void main(String[] args) {

        SpringApplication.run(ElastApplication.class);
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-app.xml");
    }
}
