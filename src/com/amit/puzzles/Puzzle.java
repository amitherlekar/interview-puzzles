package com.amit.puzzles;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Puzzle {

	public static void solveTowersOfHanoi(int n, char from, char to, char aux) {
		if (n == 1) {
			System.out.println("Move disk " + n + " from " + from + " to " + to);
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
					K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
				} else {
					K[i][w] = K[i - 1][w];
				}
				System.out.print(K[i][w] + ", ");
			}
			System.out.println();
		}

		return K[n][W];
	}

	public static Character firstNonRepeatedCharacter(String str) {

		Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();

		char[] strArray = str.toCharArray();

		int count = 0;
		for (char c : strArray) {

			if (charCountMap.containsKey(c)) {

				count = charCountMap.get(c);
				charCountMap.put(c, count + 1);

			} else {
				charCountMap.put(c, 1);

			}
		}

		for (char c : strArray) {
			count = charCountMap.get(c);
			if (count == 1)
				return new Character(c);
		}

		return null;

	}

	public static String duplicate(int[] numbers) {

		Map<Integer, Integer> numCountMap = new HashMap<>();

		int count = 0;
		for (int i : numbers) {
			if (numCountMap.containsKey(i)) {

				count = numCountMap.get(i);
				numCountMap.put(i, count + 1);

			} else {
				numCountMap.put(i, 1);
			}
		}

		Set<Integer> dups = new TreeSet<Integer>();
		for (int i : numbers) {
			count = numCountMap.get(i);

			if (count > 1) {
				dups.add(i);
			}
		}

		return dups.toString();

	}

	public static void main(String[] args) {
		// solveTowersOfHanoi(3, 'A', 'C', 'B');

	}
}
