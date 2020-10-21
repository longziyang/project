package com.project.config.note.threadpoll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPollTest {

	static AtomicInteger at = new AtomicInteger(10);

	public static void main(String[] args) {

		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		RunnableImp runnableImp = new RunnableImp(lock, condition, 100);

		ExecutorService pool1 = getThread(2);
		ExecutorService pool2 = getThread(3);
		
		pool1.execute(runnableImp);
		pool2.execute(runnableImp);

	}

	public static void AtomicIntegerTest(int n) {

		for (int i = 0; i < n; i++) {
			System.out.println(at.addAndGet(i));

		}

	}

	public static ExecutorService getThread(int i) {

		switch (i) {

		case 1:
			// ����һ�������̳߳أ�֧�ֶ�ʱ������������ִ��
			return Executors.newScheduledThreadPool(4);
		case 2:

			// �ɻ����̳߳�
			return Executors.newCachedThreadPool();
		case 3:

			// �̶��̳߳�
			return Executors.newFixedThreadPool(4);
		case 4:

			// ����һ�����̻߳����̳߳� ��ֻ����Ψһ�Ĺ����߳���ִ������
			return Executors.newSingleThreadExecutor();

		default:
			return null;
		}

	}

}
