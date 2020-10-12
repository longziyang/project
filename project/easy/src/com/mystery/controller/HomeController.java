package com.mystery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mystery.entity.Task;
import com.mystery.service.TaskService;
import com.utils.BaseController;
import com.utils.MailUtils;

@EnableAsync
@EnableScheduling
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

	@Autowired
	private TaskService taskService;

	@Scheduled(fixedDelay = 300000)
	@Async
	public void sendEmail() {

		List<Task> list = taskService.selectByState();
		try {
			MailUtils.sendTxtMail(list.get(0).getEmail(), "这是一封邮件", list.get(0).getContent());
			taskService.updateByState(list.get(0).getId());
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/view")
	public String home() {

		return "/lzy/view";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Integer> fenlei(String str) {

		String[] s = str.split("\\,");

		Map<String, Integer> map = new HashMap<>();
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;

		for (int i = 0; i < s.length; i++) {
			int c = s[i].charAt(0);
			if (c > 64 && c < 91) {
				a1++;
			} else if (c > 47 && c < 58) {
				a2++;
			} else {
				a3++;
			}
		}

		System.out.println(a1 + "-" + a2 + "-" + a3);

		map.put("a1", a1);
		map.put("a2", a2);
		map.put("a3", a3);

		return map;
	}

	@RequestMapping("/paixu")
	@ResponseBody
	public int[] array(String paixu) {
		System.out.println("paixu=" + paixu);

		String[] arr = paixu.split("\\,");
		int[] abc = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {

			abc[i] = Integer.valueOf(arr[i]);
		}

		for (int i = 0; i < abc.length - 1; i++) {

			for (int j = i + 1; j < abc.length; j++) {

				if (abc[i] > abc[j]) {
					int t = abc[i];
					abc[i] = abc[j];
					abc[j] = t;
				}
			}
		}
		return abc;
	}

}