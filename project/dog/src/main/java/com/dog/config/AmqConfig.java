package com.dog.config;

import javax.jms.Topic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqConfig {

	@Bean
	public Topic getTopic() {

		return new ActiveMQTopic("topic");
	}
}