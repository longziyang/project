package com.lzy.note.arithmetic;

public class Fibonacci {

	public static void main(String[] args) {

		getFibonacciByFor(10);
	}

	public static int getFibonacciByFor(int number) {

		int j = 1;
		int k = 1;
		int result = 0;
		int sum = 2;

		for (int i = 3; i <= number; i++) {

			result = j + k;
			j = k;
			k = result;
			sum = sum + result;
		}

		System.out.println(" sum = " + sum);
		System.out.println(" result  = " + result);
		return 0;
	}

	public static int getFibonacciByFactorial(int number) {

		return getFibonacciByFactorial(number - 1) + getFibonacciByFactorial(number - 2);
	}

}
