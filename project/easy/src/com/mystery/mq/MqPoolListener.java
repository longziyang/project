package com.mystery.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class MqPoolListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		try {
			String str = ((TextMessage) arg0).getText();
			System.out.println("被动触发的技能=" + str);
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
	}
}