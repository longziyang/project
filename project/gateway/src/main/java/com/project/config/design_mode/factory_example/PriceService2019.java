package com.project.config.design_mode.factory_example;

public class PriceService2019 implements PriceService {

	@Override
	public double getPrice16(int number, double price) {

		double finalPrice = price - price * 0.01 * number;

		if (finalPrice < price * 0.7) {

			finalPrice = price * 0.7;
		}
		return finalPrice;
	}

	@Override
	public double getPrice712(int number, double price) {

		return this.getPrice16(number, price) + 5;
	}

}
