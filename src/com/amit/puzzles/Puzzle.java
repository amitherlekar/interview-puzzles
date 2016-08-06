package com.amit.puzzles;

public class Puzzle {

	public static void solveTowersOfHanoi(int n, char from, char to, char aux) {
		if (n == 1) {
			System.out
			.println("Move disk " + n + " from " + from + " to " + to);
			return;
		}
		solveTowersOfHanoi(n - 1, from, aux, to);
		System.out.println("Move disk " + n + " from " + from + " to " + to);
		solveTowersOfHanoi(n - 1, aux, to, from);

	}

	public static int knapSack(int W, int wt[], int val[], int n) {
		int i, w;
		int[][] K = new int[n + 1][W + 1];

		// Build table K[][] in bottom up manner
		for (i = 0; i <= n; i++) {
			for (w = 0; w <= W; w++) {
				if (i == 0 || w == 0) {
					K[i][w] = 0;
				} else if (wt[i - 1] <= w) {
					K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]],
							K[i - 1][w]);
				} else {
					K[i][w] = K[i - 1][w];
				}
				System.out.print(K[i][w] + ", ");
			}
			System.out.println();
		}

		return K[n][W];
	}

	public static void main(String[] args) {
		solveTowersOfHanoi(3, 'A', 'C', 'B');

		// int val[] = { 60, 200, 120 };
		// int wt[] = { 10, 20, 30 };
		// int W = 50;
		// int n = wt.length;
		// System.out.println(knapSack(W, wt, val, n));
	}
}
