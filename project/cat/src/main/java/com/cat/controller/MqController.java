package com.cat.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
import com.cat.dao.MesgDao;
import com.cat.dao.UserDao;
import com.cat.entity.Mesg;
import com.cat.entity.User;

@Controller
public class MqController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MesgDao mesgDao;

	@JmsListener(destination = "transfer")
	public void getMessage(String str) {

		System.out.println("cat项目消费端--" + str.toString());
		Mesg mesg = mesgDao.getMesgByContent(str);
		if (mesg != null) {
			System.out.println("触发判重");
			return;
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) JSON.parse(str);
		int uid = (Integer) map.get("id");
		int mid = (Integer) map.get("mid");
		int money = (Integer) map.get("money");

		User user = userDao.findOne(uid);
		Integer u_money = Integer.parseInt(user.getBalance()) + money;
		user.setBalance(u_money.toString());

		Mesg mesg2 = new Mesg();
		mesg2.setContent(str);
		mesg2.setState(1);

		try {

		} catch (Exception e) {

		} finally {

			userDao.save(user);
			mesgDao.save(mesg2);

			restTemplate.getForObject("http://127.0.0.1:2333/user/returnOk?id=" + mid, String.class);
		}
	}
}