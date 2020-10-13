package com.project.web;

import org.apache.http.annotation.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Contract
public class MQController {

    @Autowired
    @Qualifier("jmsTopicTemplate")
    public JmsTemplate jmsTemplate;

    @RequestMapping("/add_mq")
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

    @RequestMapping("/sub_mq")
    @ResponseBody
    public String getMq() throws JMSException {// 主动消费
        Message me = jmsTemplate.receive("pool");
        String text = ((TextMessage) me).getText();
        return text;
    }
}
