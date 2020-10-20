package com.project.web;

import com.alibaba.fastjson.JSON;

import com.project.entity.Mes;
import com.project.entity.User;
import com.project.mapper.MesMapper;
import com.project.mapper.UserTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class DuckController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MesMapper mesMapper;
	@Autowired
	private UserTMapper userTMapper;

	@JmsListener(destination = "topic")
	public void readActiveTopic(String message) {
		System.out.println("接受到Duck：" + message);
		// TODO something
	}

	@JmsListener(destination = "pool")
	public void sub(String str) {
		System.out.println("duck消费" + str.toString());
	}

	@JmsListener(destination = "transfer")
	public void onMessage(String str) {

		System.out.println("duck项目消费端--" + str.toString());
		Mes mesg = mesMapper.getMesByContent(str);
		if (mesg != null) {
			System.out.println("判重");
			return;
		}

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) JSON.parse(str);
		int uid = (Integer) map.get("id");
		int mid = (Integer) map.get("mid");
		int money = (Integer) map.get("money");

		User user = userTMapper.selectByPrimaryKey(uid);
		Integer u_money = Integer.parseInt(user.getBalance()) + money;
		user.setBalance(u_money.toString());

		Mes mes2 = new Mes();
		mes2.setContent(str);
		mes2.setState(1);

		try {

		} catch (Exception e) {

		} finally {

			userTMapper.insert(user);
			mesMapper.insert(mes2);

			restTemplate.getForObject("http://127.0.0.1:2333/user/returnOk?id=" + mid, String.class);
		}
	}
}