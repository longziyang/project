//package com.mystery.config;
//
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JmsTemplateConfig {
//
//	@Autowired
//	public ConnectionFactory connectionFactory;
//
//	@Bean
//	public Connection getJmsTemplate() {
//		try {
//			Connection connection = connectionFactory.createConnection();
//			connection.start();
//			return connection;
//		} catch (Exception e) {
//		}
//
//		return null;
//	}
//
//}
