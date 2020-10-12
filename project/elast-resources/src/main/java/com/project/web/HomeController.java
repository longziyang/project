package com.project.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController{

	@RequestMapping("/")
	public String home() {
		return "home1";
	}

	@RequestMapping("getDate")
	@ResponseBody
	public String get() {

		return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
	}

	@RequestMapping("/long")
	public void lon(HttpServletResponse response) {

		int res = (int) (Math.random() * 1000 + 1);

		System.out.println(res);
		if (res % 7 == 0) {

			try {

				response.getWriter().println(res);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}