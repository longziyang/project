package com.lzy.note.pack6.controller;

import com.lzy.note.pack6.ioc.Controller;
import com.lzy.note.pack6.ioc.MyAutowired;
import com.lzy.note.pack6.service.DemoService;

@Controller
public class DemoController {

	@MyAutowired
	public DemoService demoService;

	public DemoController() {
		
		//demoService.show();
		System.out.println("controller触发");
	}
	
	

}
