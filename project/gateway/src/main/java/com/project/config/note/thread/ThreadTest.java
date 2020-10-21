package com.project.config.note.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

	public static void main(String[] args) {

		new ThreadTest().LockTest();
	}

	public void LockTest() {

		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		LockImp lockImp = new LockImp(lock, condition);
		Thread t0 = new Thread(lockImp, "��������");
		Thread t1 = new Thread(lockImp, "���۲���");
		Thread t2 = new Thread(lockImp, "��������");

		t0.start();
		t1.start();
		t2.start();

	}

	public void test() {

		ThreadImp pd = new ThreadImp();

		Thread t0 = new Thread(pd, "��������");
		Thread t1 = new Thread(pd, "���۲���");
		Thread t2 = new Thread(pd, "��������");

		t0.start();
		t1.start();
		t2.start();

	}

}
