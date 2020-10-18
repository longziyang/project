package com.duck.config;

import javax.jms.Topic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

	@Bean
	public Topic geTopic() {
		return new ActiveMQTopic("topic");
	}

}
