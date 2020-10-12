package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@MapperScan(basePackages = "com.my")
public class ElastApplication {

    public static void main(String[] args) {

        SpringApplication.run(ElastApplication.class);
    }
}
