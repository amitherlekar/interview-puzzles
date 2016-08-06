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

	public static void main(String[] args) {

		int[][] a = { { 0, 1, 2, 4, 6 }, { 7, 9, 0, 7, 3 }, { 6, 6, 6, 6, 6 } };
		setZero(a);
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