package com.project.config.design_mode.factory_example;

public class PriceService2018 implements PriceService {

	@Override
	public double getPrice16(int number, double price) {

		double finalPrice = price - price * 0.005 * number;

		if (finalPrice < price * 0.6) {

			finalPrice = price * 0.6;
		}
		return finalPrice;
	}

	@Override
	public double getPrice712(int number, double price) {

		return this.getPrice16(number, price) + 5;
	}

}
