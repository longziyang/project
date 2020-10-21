package note.callable;

import java.util.concurrent.*;

public class CallableTest {

	public static void main(String[] args) {

		FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println(Thread.currentThread().getName() + "在进行计算");
				int sum = 0;
				for (int i = 0; i < 100; i++)
					sum += i;
				return sum;
			}
		});

		//new Thread(task, "callable线程").start();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.submit(task);
		//pool.shutdown();
		System.out.println(" 主线程执行代码");

		try {
			System.out.println(Thread.currentThread().getName() + "获取子线程的结果 " + task.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
