package com.cat.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;

@Controller
public class CatMqController {

	@JmsListener(destination = "topic")
	public void readActiveTopic(String message) {
		System.out.println("接受到Cat：" + message);
		// TODO something
	}

	public CatMqController() {
		// TODO Auto-generated constructor stub
		System.out.println("go");
	}

	// @Autowired
	// private Topic topic;
	// @Autowired
	// private Queue queue;
	// @Autowired
	// private JmsTemplate jmsTemplate;

	@JmsListener(destination = "pool")
	public void sub(String str) {
		System.out.println("cat消费" + str.toString());
	}

}
