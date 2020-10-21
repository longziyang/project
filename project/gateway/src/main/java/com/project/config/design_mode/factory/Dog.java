package com.project.config.design_mode.factory;

public class Dog implements Animal {
	
	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("狗的吃方法");
	}

	@Override
	public void sing() {
		// TODO Auto-generated method stub
		System.out.println("狗的唱歌方法");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("狗的跑步方法");
	}
}
