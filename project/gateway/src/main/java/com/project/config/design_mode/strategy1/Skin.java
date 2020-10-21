package com.project.config.design_mode.strategy1;

public class Skin {

	private User user;
	private int price;

	public Skin(User user, int price) {
		this.user = user;
		this.price = price;
		user.grade();
	}

	public void buy() {
		if (user.grade == 0) {

			user.sum = price;
			System.out.println(user.sum + "购买皮肤");
		} else if (user.grade == 1) {
			user.sum = price * 9 / 10;
			System.out.println(user.sum + "购买皮肤");
		} else {
			user.sum = price * 8 / 10;
			System.out.println(user.sum + "购买皮肤");
		}
	}

}
