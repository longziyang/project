package com.mystery.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.utils.BaseController;

@Controller
@RequestMapping("/mq")
public class MqController extends BaseController {

	@Autowired
	@Qualifier("jmsTopicTemplate")
	public JmsTemplate jmsTemplate;

	@RequestMapping("/addMq")
	@ResponseBody
	public String setMq(String str) {

		jmsTemplate.send("pool", new MessageCreator() {

			@Override
			public Message createMessage(Session arg0) throws JMSException {
				TextMessage tm = arg0.createTextMessage(str);
				return tm;
			}
		});
		return "ok";
	}

	@RequestMapping("/subMq")
	@ResponseBody
	public String getMq() throws JMSException {// 主动消费
		Message me = jmsTemplate.receive("pool");
		String text = ((TextMessage) me).getText();
		return text;
	}

}
