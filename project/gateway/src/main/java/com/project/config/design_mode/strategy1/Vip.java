package com.project.config.design_mode.strategy1;

public class Vip extends User {

	@Override
	public void grade() {
		super.grade = 1;
		System.out.println("我是普通会员");
	}

}
