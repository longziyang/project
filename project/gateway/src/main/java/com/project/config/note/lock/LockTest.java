package com.project.config.note.lock;

public class LockTest {

	public static void main(String[] args) {
		
		ThreadTest threadTest = new ThreadTest(100);
		
		Thread thread1=new Thread(threadTest);
		Thread thread2=new Thread(threadTest);
		
		thread1.setName("һ�Ŵ���");
		thread2.setName("���Ŵ���");
		
		thread1.start();
		thread2.start();

	}

}
