package com.project.config.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class AmpTest {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:1234");
        JmsTemplate jmsTemplate = new JmsTemplate(factory);
        jmsTemplate.setPubSubDomain(false);

        jmsTemplate.send("ooods", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                TextMessage textMessage = session.createTextMessage("send message");//(TextMessage) session.createMessage();
                return textMessage;
            }
        });


    }
}

