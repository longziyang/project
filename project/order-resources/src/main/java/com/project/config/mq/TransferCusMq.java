package com.project.config.mq;

import com.alibaba.fastjson.JSON;
import com.project.entity.Mes;
import com.project.entity.Order;
import com.project.entity.SysUser;
import com.project.feign.UserFeign;
import com.project.mapper.MesMapper;
import com.project.mapper.UserMapper;
import org.codehaus.jackson.util.JsonParserSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class TransferCusMq implements MessageListener {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MesMapper mesMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private UserFeign userFeign;


    @Override
    public void onMessage(Message arg0) {
        try {
            String str = ((TextMessage) arg0).getText();
            System.out.println("被动触发的技能=" + str);
        } catch (JMSException e1) {
            e1.printStackTrace();
        }
    }

    @JmsListener(destination = "order_integral")
    public String mq(String content) {

        Mes mes = mesMapper.getMesByContent(content);
        if (mes != null) {
            return "触发判重";
        }

        Map<String, Object> map = JSON.parseObject(content, Map.class);

        Integer integral = (Integer) map.get("integral");
        Long orderId = (Long) map.get("order_id");

        //不影响下单  写进数据库  或mq 定时执行
        if (integral < 0) {

            return "订单积分错误";
        }
        Long userId = (Long) map.get("userId");
        SysUser user = userMapper.selectById(userId);
        integral += user.getIntegral();

        userMapper.updateIntegralById(integral, userId);

        Mes mes2 = new Mes();
        mes2.setState(1);

        mesMapper.insert(mes2);
        return userFeign.transfer(mes2.getId().toString());

    }
}
