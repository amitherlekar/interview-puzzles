package com.amit.sort;

public class QuickSort {

	public void quickSort(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Array cannot be empty");
		}
		sort(array, 0, array.length - 1);
	}

	private void sort(int[] array, int low, int high) {
		if (low < high) {
			int pivot = partition(array, low, high);
			sort(array, low, pivot - 1);
			sort(array, pivot + 1, high);
		}
	}

	private int partition(int[] array, int low, int high) {

		int i = low, j = high;
		int p = array[low];

		while (i < j) {
			while (array[i] <= p && i < high) {
				i++;
			}
			while (array[j] > p) {
				j--;
			}
			if (i < j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		array[low] = array[j];
		array[j] = p;
		return j;
	}

	public static void main(String[] args) {
		QuickSort m = new QuickSort();
		int[] a = new int[] { 11, 23, 1, 333, 21, 3, 9, 12 };
		m.quickSort(a);
		for (int x : a) {
			System.out.print(x + ",");
		}
		System.out.println();

	}

}
