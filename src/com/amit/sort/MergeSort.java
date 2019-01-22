package com.amit.sort;

public class MergeSort {
	public void sortArray(int[] array) {
		sort(array);

	}

	private void sort(int[] array) {

		if (array.length > 1) {
			int[] left = leftArray(array);
			int[] right = rightArray(array);
			sort(left);
			sort(right);
			merge(array, left, right);
		}
	}

	private int[] leftArray(int[] array) {
		int size = array.length / 2;
		int[] left = new int[size];
		for (int i = 0; i < size; i++) {
			left[i] = array[i];
		}
		return left;
	}

	private int[] rightArray(int[] array) {
		int size1 = array.length / 2;
		int size2 = array.length - size1;
		int[] right = new int[size2];
		for (int i = 0; i < size2; i++) {
			right[i] = array[i + size1];
		}
		return right;
	}

	private void merge(int[] array, int[] left, int[] right) {
		int i = 0, j = 0, k = 0;

		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				array[k++] = left[i++];
			} else {
				array[k++] = right[j++];
			}
		}
		while (i < left.length) {
			array[k++] = left[i++];

		}
		while (j < right.length) {
			array[k++] = right[j++];
		}
	}

	public static void main(String[] args) {
		MergeSort m = new MergeSort();
		int[] a = new int[] { 103, 111, 99 };
		m.sortArray(a);
		for (int x : a) {
			System.out.print(x + ",");
		}
		System.out.println();

	}
}
