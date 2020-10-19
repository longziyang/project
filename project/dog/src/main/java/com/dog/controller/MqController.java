package com.dog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dog.dao.MesDao;
import com.dog.entity.Mes;
import com.dog.service.MqService;

@Controller
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
@RequestMapping("/user")
public class MqController {

	@Autowired
	private MqService mqService;
	@Autowired
	private MesDao mesDao;
	@Autowired
	private JmsTemplate jmsTemplate;

	@RequestMapping("/transfer")
	@ResponseBody
	public String transfer(int money) {

		String result = mqService.transfer(money);

		return result;
	}

	@RequestMapping("/returnOk")
	@ResponseBody
	public String returnOk(Integer id) {

		Optional<Mes> optional = mesDao.findById(id);
		if (optional.isPresent()){

			Mes mes = optional.get();
			mes.setState(1);
			mesDao.save(mes);
			return "ok";
		}

		return "mes is null";
	}

	@Scheduled(fixedDelay = 1000)
	@Async
	public void seek() {
		List<Mes> list = mesDao.getMesByState();

		for (Mes mes : list) {
			jmsTemplate.convertAndSend("transfer", mes.getContent());
		}
	}

}
