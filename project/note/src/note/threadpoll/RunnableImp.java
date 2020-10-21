package note.threadpoll;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class RunnableImp implements Runnable {

	private Lock lock;
	private Condition condition;
	private Integer number;

	public RunnableImp(Lock lock, Condition condition, Integer number) {

		this.condition = condition;
		this.lock = lock;
		this.number = number;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (number > 0) {

			lock.lock();
			condition.signalAll();
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (number > 0) {

				number--;
				System.out.println(Thread.currentThread().getName() + "   Ê£ÓàÆ±Êı" + number);
			}

			lock.unlock();
		}

	}

}
