package com.project.config.design_mode.factory;

public class Duck implements Animal{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("鸭子的吃方法");
	}

	@Override
	public void sing() {
		// TODO Auto-generated method stub
		System.out.println("鸭子的唱歌方法");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("鸭子的跑步方法");
	}

}
