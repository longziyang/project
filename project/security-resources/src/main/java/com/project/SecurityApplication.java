package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication()
@MapperScan("com.project.mapper")
//@EnableFeignClients
@EnableEurekaClient
public class SecurityApplication {

    public static void main(String[] args) {

        SpringApplication.run(SecurityApplication.class);
    }
}
