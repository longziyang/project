package com.project.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;

@Configuration
public class AmqConfig {

    @Bean
    public Topic getTopic() {
        return new ActiveMQTopic("topic");
    }

}
