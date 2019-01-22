package com.amit.sort;

public class BinarySearch {

	public static int searchIteratively(int[] a, int key) {
		int low = 0;
		int high = a.length;
		int mid = 0;
		while (low < high) {
			mid = (low + high) / 2;
			if (key == a[mid]) {
				return mid;
			} else if (key < a[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return -1;
	}

	public static int searchRecursively(int[] a, int key, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			if (key == a[mid]) {
				return mid;
			} else if (key < a[mid]) {
				high = mid - 1;
				return searchRecursively(a, key, low, high);
			} else {
				low = mid + 1;
				return searchRecursively(a, key, low, high);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 6, 7, 45, 48, 74, 88 };
		// System.out.println(searchIteratively(a, 999));

		System.out.println(searchRecursively(a, 6989, 0, a.length - 1));
	}

}
