package com.project.config.design_mode.strategy;

public class MainClass {

	public static void main(String[] args) {

		Test test = new Test(new Dog());
		test.eat();
	}
}
