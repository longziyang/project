package com.project.config.design_mode.strategy1;

public class Shop {

	public static void main(String[] args) {
		Skin skin = new Skin(new Svip(), 100);
		skin.buy();
	}

}
