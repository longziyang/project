package com.project.config.note.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class LockImp implements Runnable {

	private Lock lock;
	private Condition condition;
	private String name = "��������";

	public LockImp(Lock lock, Condition condition) {
		this.condition = condition;
		this.lock = lock;
	}

	@Override
	public void run() {

		lock.lock();
		for (int i = 0; i < 100; i++) {

			System.out.println(Thread.currentThread().getName() + "  " + name);

			if (Thread.currentThread().getName().equals("��������")) {

				// System.out.println("��Ʒ������������");
				if (name.equals("��������")) {

					System.out.println("��Ʒ�ӹ����");
					name = "��������";
				} else {
					condition.signalAll();
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else if (Thread.currentThread().getName().equals("���۲���")) {

				// System.out.println("��Ʒ�������۲���");
				if (name.equals("��������")) {

					System.out.println("��Ʒ�������");
					name = "���۲���";
				} else {
					condition.signalAll();
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			} else if (Thread.currentThread().getName().equals("��������")) {

				// System.out.println("��Ʒ������������");
				if (name.equals("���۲���")) {

					System.out.println("��Ʒ�������");
					name = "��������";
				} else {
					condition.signalAll();
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}

	}

}
