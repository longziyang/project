package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@EnableEurekaClient
@MapperScan("com.project.mapper")
public class StockApplication {

    public static void main(String[] args) {

        SpringApplication.run(StockApplication.class);
    }
}
