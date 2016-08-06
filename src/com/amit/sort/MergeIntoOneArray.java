package com.amit.sort;

public class MergeIntoOneArray {

	public static void mergeBwithA(int[] a, int[] b, int lastOfA) {
		int i = lastOfA - 1;
		int j = b.length - 1;
		int c = i + j - 1;

		while (j >= 0 && c >= 0) {
			if (i >= 0 && a[i] > b[j]) {
				a[c] = a[i];
				c--;
				i--;
			} else {
				a[c] = b[j];
				j--;
				c--;
			}

		}
	}

	public static void main(String[] args) {
		int a[] = new int[7];
		int b[] = new int[3];

		a[0] = 6;
		a[1] = 7;
		a[2] = 8;
		b[0] = 3;
		b[1] = 4;
		b[2] = 5;
		mergeBwithA(a, b, 4);

		for (int i : a) {
			System.out.print(i + ", ");
		}

	}

}
