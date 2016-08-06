package com.amit.math;

public class GcdLcm {

	/**
	 * Euclid's algorithm
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static int gcd(int x, int y) {
		int r = 0, a, b;
		a = (x > y) ? x : y; // a is greater number
		b = (x < y) ? x : y; // b is smaller number
		// r = b;
		while (a % b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return r;
	}

	public static int lcm(int x, int y) {
		int a;
		a = (x > y) ? x : y; // a is greater number
		while (true) {
			if (a % x == 0 && a % y == 0)
				return a;
			++a;
		}
	}

	public static void permute(char[] a, int k) {
		if (k == a.length) {
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i]);
			}
			System.out.println();
		} else {
			for (int i = k; i < a.length; i++) {
				char temp = a[k];
				a[k] = a[i];
				a[i] = temp;

				permute(a, k + 1);

				temp = a[k];
				a[k] = a[i];
				a[i] = temp;
			}
		}

	}

	public static void main(String[] args) {
		System.out.println(gcd(64, 40));
	}
}
