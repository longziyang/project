package note.arithmetic;

import java.util.Arrays;

public class Quicksort {

	public static void main(String[] args) {

		int[] array = { 10, 26, 36, 85, 45, 14, 51 };
		quicksort(array, 0, array.length - 1);

	}

	public static void quicksort(int[] array, int begin, int end) {

		if (begin < end) {

			int mid = index(array, begin, end);
			quicksort(array, begin, mid - 1);
			quicksort(array, mid + 1, end);

		}

		System.out.println(Arrays.toString(array));

	}

	public static int index(int[] array, int begin, int end) {

		int key = array[begin];

		while (begin < end) {

			while (begin < end && key <= array[end]) {

				end--;
			}
			
			array[begin] = array[end];
			begin++;
			while (begin < end && key >= array[begin]) {

				begin++;
			}
			array[end] = array[begin];

		}
		array[begin] = key;
		return begin;

	}

}
