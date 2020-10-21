package com.project.config.design_mode.factory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

class FactoryAnimal {

	public Animal getAnimal(String s) throws Exception {

		Properties p = new Properties();
		p.load(new FileInputStream(
				new File("C:\\Users\\samsung\\eclipse-workspace\\note\\src\\design_mode\\factory\\config.properties")));
		String str = p.getProperty(s);

		Animal animal = (Animal) Class.forName(str).newInstance();
		return animal;

	}

}
