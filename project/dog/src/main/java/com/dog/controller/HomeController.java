package com.dog.controller;

import javax.jms.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dog.dao.UserDao;
import com.dog.entity.User;

@Controller
public class HomeController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private Topic topic;
	@Autowired
	private JmsTemplate jmsTemplate;

	@RequestMapping("/")
	public String home(Model model) {

		User user = userDao.findOne(1);
		model.addAttribute("user", user);

		jmsTemplate.convertAndSend("pool", user.toString());
		System.out.println("加入pool池");
		return "home";
	}

	@RequestMapping("/poll")
	public String poll() {

		return "poll";
	}

}
