package com.dog.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PollController {

	@RequestMapping("/getTime")
	@ResponseBody
	public String getTime() {

		String date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
		return date;
	}

	@RequestMapping("/longPoll")
	@ResponseBody
	public String longPoll(HttpServletResponse response) {

		// Calendar calendar = Calendar.getInstance();
		// int minute = calendar.get(Calendar.MINUTE);
		//
		// if (minute == 0) {
		// try {
		// response.getWriter().print(minute);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }

		int number = (int) (Math.random() * (9999 - 1000 + 1) + 1000);

		System.out.println(number);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (number > 5000) {
			try {
				response.getWriter().print(number);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
