package com.lzy.note.forkjoin;

import java.util.concurrent.RecursiveTask;

public class SumTest extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int start;
	private int end;

	public SumTest(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {

		if (end - start < 1000) {

			int sum = 0;
			for (int i = start; i <= end; i++) {

				sum += i;
			}

			return sum;
		}

		int sum = 0;
		for (int i = start; i <= end; i += 1000) {

			SumTest sumDemo = new SumTest(i, i + 999);
			sum += sumDemo.compute();

		}

		return sum;

	}

	public Long timeDifference() {

		Long startTime = System.currentTimeMillis();
		customMethods();
		Long endTime = System.currentTimeMillis();
		System.out.println(endTime + "  " + startTime);
		return endTime - startTime;
	}

	public void customMethods() {

	}

}
