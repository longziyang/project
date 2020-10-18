package com.dog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dog.dao.MesDao;
import com.dog.dao.UserDao;
import com.dog.entity.Mes;
import com.dog.entity.User;

@Service
public class MqService {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MesDao mesDao;

	public String transfer(int money) {

		System.out.println("访问余额宝项目");
		User user = userDao.findOne(1);
		int balance = Integer.parseInt(user.getBalance());

		if (balance - money > 0) {

			int newBalance = balance - money;
			Integer a = newBalance;
			user.setBalance(a.toString());
			userDao.save(user);

			Mes mes = new Mes();
			mes.setState(0);
			mes.setContent("");
			mesDao.save(mes);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("money", money);
			map.put("id", 1);
			map.put("mid", mes.getId());

			mes.setContent(JSON.toJSONString(map));

			mesDao.save(mes);

			jmsTemplate.convertAndSend("transfer", JSON.toJSONString(map));

			return "请求已收到";
		}

		return "余额不足";

	}

}
