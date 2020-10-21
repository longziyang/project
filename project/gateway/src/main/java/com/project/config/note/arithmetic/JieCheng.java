package com.project.config.note.arithmetic;

public class JieCheng {

	public static void main(String[] args) {

		System.out.println(array(7));
	}

	public static int array(int number) {

		if (number < 3) {
			System.out.println(3);
			return 2;
		}

		int result = 1;
		int sum = 0;

		for (int i = 1; i <= number; i++) {

			result = result * i;
			sum = result + sum;
		}

		System.out.println(sum);
		return result;
	}

	public static int factorial(int number) {

		if (number < 3) {

			System.out.println(3);
			return 2;
		}

		int sum = 0 ;
		sum = sum + number * factorial(number - 1);
		return number * factorial(number - 1);

	}

}
