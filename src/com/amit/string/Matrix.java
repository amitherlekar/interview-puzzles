package com.amit.string;

import java.util.ArrayList;

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

	// Clockwise spiral
	public static ArrayList<Integer> findSpiral(int[][] arr) {
		ArrayList<Integer> spiralOrder = new ArrayList<Integer>();
		if (arr == null || arr.length == 0)
			return spiralOrder;
		int m = arr.length, n = arr[0].length;
		int x = 0, y = 0;
		while (m > 0 && n > 0) {
			if (m == 1) {
				for (int i = 0; i < n; i++)
					spiralOrder.add(arr[x][y++]);
				break;
			} else if (n == 1) {
				for (int i = 0; i < m; i++)
					spiralOrder.add(arr[x++][y]);
				break;
			}
			for (int i = 0; i < n - 1; i++)
				spiralOrder.add(arr[x][y++]);
			for (int j = 0; j < m - 1; j++)
				spiralOrder.add(arr[x++][y]);
			for (int i = 0; i < n - 1; i++)
				spiralOrder.add(arr[x][y--]);
			for (int j = 0; j < m - 1; j++)
				spiralOrder.add(arr[x--][y]);
			x++;
			y++;
			m = m - 2;
			n = n - 2;
		}
		return spiralOrder;
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