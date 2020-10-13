package com.project.mq;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class MQPoolListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String str = null;
        try {
            str = ((TextMessage) message).getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        System.out.println("mq接收到的消息： " + str);
    }
}
