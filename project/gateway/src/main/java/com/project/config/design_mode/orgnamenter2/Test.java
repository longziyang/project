package com.project.config.design_mode.orgnamenter2;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) throws Exception {

		ProxyStart pro = new ProxyStart(new StartIpm("黄渤"));
		Start start = (Start) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[] { Start.class }, pro);
		start.movie(550);
	}
}
