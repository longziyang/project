package com.duck.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;

@Controller
public class DuckMqController {

	@JmsListener(destination = "topic")
	public void readActiveTopic(String message) {
		System.out.println("接受到Duck：" + message);
		// TODO something
	}

	@JmsListener(destination = "pool")
	public void sub(String str) {
		System.out.println("duck消费" + str.toString());
	}

}
