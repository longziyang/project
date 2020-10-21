package com.project.config.design_mode.factory;

public class Start {

	public static void main(String[] args) throws Exception {

		Animal animal = new FactoryAnimal().getAnimal("type");
		animal.eat();
	}

}
