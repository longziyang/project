package com.project.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLog {

    @Bean
    public Logger.Level log() {

        return Logger.Level.FULL;
    }
}
