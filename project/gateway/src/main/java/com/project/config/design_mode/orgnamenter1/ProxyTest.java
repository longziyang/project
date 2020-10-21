package com.project.config.design_mode.orgnamenter1;

public class ProxyTest {

	private Person person;

	public ProxyTest() {
		this.person = new Person();
	}

	public void clean(int money) {

		if (money > 500) {

			person.clean();
			return;
		}

		System.out.println(money + "？这点钱 你自己打扫吧");
	}

}
