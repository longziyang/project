package note.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest extends Thread {

	ReentrantLock lock = new ReentrantLock();

	int number;

	public ThreadTest(int number) {
		this.number = number;
	}

	@Override
	public void run() {

		while (number > 0) {

			// synchronized (this) {


			lock.lock();
			if (number > 0) {

				System.out.println(Thread.currentThread().getName() + " 卖出一张票;  剩余票数：" + number--);

			}

			lock.unlock();

			// }

		}

	}

}
