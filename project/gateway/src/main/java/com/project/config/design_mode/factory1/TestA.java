package com.project.config.design_mode.factory1;

public class TestA {

	public static void main(String[] args) throws Exception {

		String type = "design_mode.factory1.Person";
		String name = "eat";

		Object obj = Class.forName(type).newInstance();
		Person.class.getDeclaredMethod(name).invoke(obj);

	}
}
