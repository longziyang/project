 package com.project.config.design_mode.strategy;

public class Test {

	private Animal animal;

	public Test(Animal animal) {
		this.animal = animal;
	}

	public void eat() {
		animal.eat();
	}

}
