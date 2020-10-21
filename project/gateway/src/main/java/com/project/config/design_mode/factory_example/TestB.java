package com.project.config.design_mode.factory_example;

import java.util.Date;
import java.util.Scanner;

public class TestB {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("�������Ź��۸�");
		double price = in.nextDouble();
		System.out.println("����������");
		int number = in.nextInt();

		PriceService priceService = new FactoryPrice().getPrice("type");

		Date date = new Date();
		@SuppressWarnings("deprecation")
		int month = date.getMonth() + 1;
		double finalPrice;

		if (month >= 1 && month <= 6) {
			finalPrice = priceService.getPrice16(number, price);
		} else {
			finalPrice = priceService.getPrice712(number, price);
		}

		System.out.println(finalPrice);

	}

}
