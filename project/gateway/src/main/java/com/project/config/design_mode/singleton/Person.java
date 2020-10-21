package com.project.config.design_mode.singleton;

public class Person {

	private static Person person = new Person();

	private Person() {

	}

	public Person getPerson() {
		return person;
	}

}
