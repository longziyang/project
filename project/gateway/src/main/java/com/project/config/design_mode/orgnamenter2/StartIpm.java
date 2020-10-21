package com.project.config.design_mode.orgnamenter2;

public class StartIpm implements Start {

	public String name;

	public StartIpm(String name) {
		this.name = name;
	}

	@Override
	public void movie(int money) {

		System.out.println(name + "出演电影 报酬：" + money);
	}

}
