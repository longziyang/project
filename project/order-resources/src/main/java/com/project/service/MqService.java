package com.project.service;

import com.alibaba.fastjson.JSON;
import com.project.entity.Mes;
import com.project.entity.User;
import com.project.mapper.MesMapper;
import com.project.mapper.UserTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MqService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private UserTMapper userTMapper;
    @Autowired
    private MesMapper mesMapper;

    public String transfer(int money) {

        System.out.println("访问余额宝项目");
        User user = new User();
        int balance = Integer.parseInt(user.getBalance());

        if (balance - money > 0) {

            int newBalance = balance - money;
            Integer a = newBalance;
            user.setBalance(a.toString());
            userTMapper.insert(user);

            Mes mes = new Mes();
            mes.setState(0);
            mes.setContent("");
            mesMapper.insert(mes);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("money", money);
            map.put("id", 1);
            map.put("mid", mes.getId());

            mes.setContent(JSON.toJSONString(map));

            mesMapper.insert(mes);

            jmsTemplate.convertAndSend("transfer", JSON.toJSONString(map));

            return "请求已收到";
        }

        return "余额不足";

    }

    public String returnOk(Integer id) {

        Mes mes = mesMapper.selectByPrimaryKey(id);
        if (mes != null) {

            mes.setState(1);
            mesMapper.insert(mes);
            return "ok";
        }

        return "mes is null";
    }

    public void seek() {

        List<Mes> list = mesMapper.getMesByState();

        for (Mes mes : list) {
            jmsTemplate.convertAndSend("transfer", mes.getContent());
        }
    }
}
