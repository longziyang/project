package com.lzy.note.thread;

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
		Thread t0 = new Thread(lockImp, "生产部门");
		Thread t1 = new Thread(lockImp, "销售部门");
		Thread t2 = new Thread(lockImp, "物流部门");

		t0.start();
		t1.start();
		t2.start();

	}

	public void test() {

		ThreadImp pd = new ThreadImp();

		Thread t0 = new Thread(pd, "生产部门");
		Thread t1 = new Thread(pd, "销售部门");
		Thread t2 = new Thread(pd, "物流部门");

		t0.start();
		t1.start();
		t2.start();

	}

}
