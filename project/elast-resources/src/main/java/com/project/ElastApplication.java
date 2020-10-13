package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@MapperScan(basePackages = "com.my")
public class ElastApplication {

    public static void main(String[] args) {

        SpringApplication.run(ElastApplication.class);
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-app.xml");
    }
}
