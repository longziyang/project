package note.arithmetic;

import java.util.Arrays;

public class ErFenFa {

	public static void main(String[] args) {

		int[] array = { 10, 5, 23, 16, 99, 85, 44, 56 };
		Arrays.sort(array);
		// System.out.println(select(array, 99));
		System.out.println(factorial(array, 85, 0, array.length - 1));

	}

	public static int select(int[] array, int key) {

		int begin = 0;

		int end = array.length - 1;

		while (begin <= end) {

			int mid = (begin + end) / 2;

			if (key == array[mid]) {

				return mid;

			} else if (key > array[mid]) {

				begin = mid + 1;
			} else if (key < array[mid]) {

				end = mid - 1;
			}

		}
		return -1;

	}

	public static int factorial(int[] array, int key, int begin, int end) {

		while (begin <= end) {

			int mid = (begin + end) / 2;
			if (key == array[mid]) {

				return mid;
			} else if (key > array[mid]) {

				return factorial(array, key, mid + 1, end);
			} else if (key < array[mid]) {

				return factorial(array, key, begin, mid - 1);
			}

		}

		return -1;
	}

}
