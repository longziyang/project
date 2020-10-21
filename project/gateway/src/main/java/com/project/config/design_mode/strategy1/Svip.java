package com.project.config.design_mode.strategy1;

public class Svip extends User {

	@Override
	public void grade() {
		// TODO Auto-generated method stub
		super.grade = 2;
		System.out.println("我是超级会员");
	}

}
