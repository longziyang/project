
package com.project.config.lock;


public class Test {

	public static void main(String[] args) {

		RunnableImp runnableImp = new RunnableImp( "lockKey");

		Thread t1 = new Thread(runnableImp);
		Thread t2 = new Thread(runnableImp);

		t1.start();
		t2.start();

	}

}
