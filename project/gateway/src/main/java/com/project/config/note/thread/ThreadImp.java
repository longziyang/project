package com.project.config.note.thread;

public class ThreadImp implements Runnable {

	private String name = "��������";

	@Override
	public void run() {

		synchronized (this) {

			for (int i = 0; i < 100; i++) {

				System.out.println(Thread.currentThread().getName() + "  " + name);

				if (Thread.currentThread().getName().equals("��������")) {

					// System.out.println("��Ʒ������������");
					if (name.equals("��������")) {

						System.out.println("��Ʒ�ӹ����");
						name = "��������";
					} else {
						notifyAll();
						try {
							wait();
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
						notifyAll();
						try {
							wait();
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
						notifyAll();
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

		}
	}

}
