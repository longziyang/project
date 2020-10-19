package com.project.config.mq;

import com.alibaba.fastjson.JSON;
import com.project.entity.Mesg;
import com.project.entity.Order;
import com.project.entity.SysUser;
import com.project.feign.UserFeign;
import com.project.mapper.MesgMapper;
import com.project.mapper.UserMapper;
import org.codehaus.jackson.util.JsonParserSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransferCusMq {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MesgMapper mesgMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private UserFeign userFeign;


    @JmsListener(destination = "order_integral")
    public String mq(String content) {

        Mesg mesg = mesgMapper.getMesgByContent(content);
        if (mesg != null) {
            return "触发判重";
        }

        Map<String,Object> map = JSON.parseObject(content,Map.class);

        Integer integral = (Integer) map.get("integral");
        Long orderId = (Long) map.get("order_id");

        //不影响下单  写进数据库  或mq 定时执行
        if (integral < 0) {

            return "订单积分错误";
        }
        Long userId = (Long) map.get("userId");
        SysUser user = userMapper.selectById(userId);
        integral += user.getIntegral();

        userMapper.updateIntegralById(integral,userId);

        Mesg mesg2 = new Mesg();
        mesg2.setState(1);

        mesgMapper.insert(mesg2);
        return  userFeign.transfer(mesg2.getId().toString());

    }
}
