package com.lzy.note.pack6.service;

import com.lzy.note.pack6.dao.DemoDao;
import com.lzy.note.pack6.ioc.MyAutowired;
import com.lzy.note.pack6.ioc.Service;

@Service
public class DemoService {

	@MyAutowired
	public DemoDao demoDao;

	public DemoService() {

		System.out.println("service触发");
	}

	public void show() {
		System.out.println("service的show方法");
	}

}
