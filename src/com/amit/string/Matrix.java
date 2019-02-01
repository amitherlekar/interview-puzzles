package com.amit.string;

public class Matrix {

	public static void setZero(int[][] a) {

		if (a == null || a.length == 0) {
			throw new IllegalArgumentException("array is null or empty");
		}

		boolean[] row = new boolean[a.length];
		boolean[] column = new boolean[a[0].length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (a[i][j] == 0) {
					row[i] = true;
					column[j] = true;

				}
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (row[i] || column[j]) {
					a[i][j] = 0;
				}
			}
		}
	}

	public static void flipItVerticalAxis(int[][] matrix) {
		int temp = 0;

		System.out.println("Matrix row length = " + matrix.length);
		for (int i = 0; i < matrix.length; i++) {
			System.out.println("Matrix column length = " + matrix[i].length);
			for (int j = 0; j < matrix[0].length / 2; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
				matrix[i][matrix[i].length - 1 - j] = temp;

				// swap(matrix[i][j], matrix[i][matrix[i].length - 1 - j]);
			}
		}
	}

	public static void swap(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
	}

	public static void main(String[] args) {

		int[][] a = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		flipItVerticalAxis(a);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (j > 0)
					System.out.print(", ");
				System.out.print(a[i][j]);
			}
			System.out.println();
		}

	}

}