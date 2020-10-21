package com.project.config.design_mode.singleton;

public class Animal {

	private static Animal animal = null;

	private Animal() {

	}

	public Animal getAnimal() {

		if (animal == null) {

			synchronized (this) {
				if (animal == null) {

					return new Animal();
				}

			}

		}

		return animal;
	}

}
