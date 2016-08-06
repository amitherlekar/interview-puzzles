package com.amit.practice;

public class MergeSort {
	public void sort(int[] a) {

		if (a.length > 1) {
			int[] left = getLeft(a);
			int[] right = getRight(a);
			sort(left);
			sort(right);
			merge(a, left, right);
		}

	}

	private int[] getLeft(int[] a) {
		int size = a.length / 2;
		int[] left = new int[size];
		for (int i = 0; i < size; i++) {
			left[i] = a[i];
		}
		return left;
	}

	private int[] getRight(int[] a) {
		int size = a.length / 2;
		int size2 = a.length - size;
		int[] right = new int[size2];
		for (int i = 0; i < size2; i++) {
			right[i] = a[size + i];
		}
		return right;
	}

	private void merge(int[] a, int[] left, int[] right) {
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < left.length && j < right.length) {

			if (left[i] <= right[j]) {
				a[k++] = left[i++];
			} else {
				a[k++] = right[j++];
			}
		}
		while (i < left.length) {
			a[k++] = left[i++];
		}
		while (j < right.length) {
			a[k++] = right[j++];
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] { 9, 8, 7, 6, 5, 4, 3 };

		MergeSort algorithm = new MergeSort();
		algorithm.sort(a);
		for (int i = 0; i < a.length; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(a[i]);
		}
		System.out.println();
	}
}
