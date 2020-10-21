package com.project.config.design_mode.factory_example;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class FactoryPrice {

	public PriceService getPrice(String type) {

		try {
			Properties pro = new Properties();
			pro.load(new FileInputStream(new File("C:\\Users\\samsung\\eclipse-workspace\\w6-03\\src\\pack0\\config.properties")));

			String str = pro.getProperty(type);
			PriceService p = (PriceService) Class.forName(str).newInstance();

			return p;

		} catch (Exception e) {
			System.out.println("有误");
		}

		return null;
	}

}
