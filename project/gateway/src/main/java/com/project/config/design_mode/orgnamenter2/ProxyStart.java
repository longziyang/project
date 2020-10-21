package com.project.config.design_mode.orgnamenter2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyStart implements InvocationHandler {

	private Object object;

	public ProxyStart(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if (method.getName().equals("movie") && args[0] instanceof Integer) {
			if ((Integer) args[0] < 500) {
				System.out.println((Integer) args[0] + "这点钱？你让蔡徐坤来演把");
				return null;
			}
		}
		method.invoke(object, args);
		return object;
	}
}
