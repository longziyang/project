package com.project.config;

import org.springframework.beans.factory.InitializingBean;

public class MyService implements InitializingBean {

	public MyService() {
		System.out.println("触发构造");
	}

	public void init() {
		System.out.println("init-method is called");
		System.out.println("******************************");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("******************************");
		System.out.println("afterPropertiesSet is called");
		System.out.println("******************************");

	}

}
