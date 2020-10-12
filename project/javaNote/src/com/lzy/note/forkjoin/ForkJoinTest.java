package com.lzy.note.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinTest {

	public static void main(String[] args) {

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		SumTest sumTest = new SumTest(1, 1000000);

		Long startTime = System.currentTimeMillis();
		ForkJoinTask<Integer> task = forkJoinPool.submit(sumTest);
		Long endTime = System.currentTimeMillis();

		try {
			System.out.println(task.get());
			System.out.println(endTime + "  " + startTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(System.currentTimeMillis());
		int sum = 0;
		for (int i = 1; i <= 1000000; i++) {

			sum = sum + i;
		}
		System.out.println(System.currentTimeMillis() + " =" + sum);
	}

}
