package com.amit.graph;

import java.util.ArrayList;
import java.util.Stack;

public class DFS {

	private int[][] graph;
	private boolean visited[];

	public DFS(int[][] graph) {
		if (graph == null || graph.length == 0) {
			throw new IllegalArgumentException("Graph cannot be null");
		}
		this.graph = graph;
		visited = new boolean[graph.length];
	}

	public void traverse() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		visited[0] = true;
		System.out.print(0 + ", ");

		while (!stack.isEmpty()) {
			int node = stack.peek();
			int child = getUnvisitedNode(node);

			if (child > 0) {
				visited[child] = true;
				stack.push(child);
				System.out.print(child + ", ");
			} else {
				stack.pop();
			}
		}
	}

	private int getUnvisitedNode(int node) {
		for (int i = 0; i < graph.length; i++) {
			if (graph[node][i] > 0) {
				if (!visited[i]) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * You're given a 2D board which contains an m x n matrix of chars - char[][]
	 * board. Write a method - printPaths that prints all possible paths from the
	 * top left cell to the bottom right cell. Your method should return an
	 * ArrayList of Strings, where each String represents a path with characters
	 * appended in the order of movement. You're only allowed to move down and right
	 * on the board. The order of String insertion in the ArrayList does not matter.
	 * 
	 * @param board
	 * @return
	 */
	public ArrayList<String> printPaths(char[][] board) {
		ArrayList<String> out = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		search(0, 0, board, sb, out);
		return out;
	}

	public void search(int i, int j, char[][] board, StringBuilder sb, ArrayList<String> out) {
		int rows = board.length;
		int cols = board[0].length;
		if (i > rows - 1 || j > cols - 1)
			return;

		sb.append(board[i][j]); // Mark
		if (i == rows - 1 && j == cols - 1) {
			out.add(sb.toString());
			sb.deleteCharAt(sb.length() - 1);
			return;
		}
		search(i + 1, j, board, sb, out); // Search Down
		search(i, j + 1, board, sb, out); // Search Right
		sb.deleteCharAt(sb.length() - 1); // Un-Mark
	}

	/**
	 * You're given a game board that has m x n squares on it, represented by an m x
	 * n array. Write a method - countPaths that takes in m and n and returns the
	 * number of possible paths from the top left corner to the bottom right corner.
	 * Only down and right directions of movement are permitted.
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int countPaths(int m, int n) {
		if (m == 1 || n == 1)
			return 1;
		int memo[][] = new int[m][n];

		/*
		 * Fill the first row and column of memo with 1s. This is because the direction
		 * of movement is limited to down and right when moving from start to finish. If
		 * you reverse the direction of movement and think about the number of paths
		 * from a cell to the starting cell, all cells in the first row have exactly 1
		 * path (straight left) and all cells in the first column have exactly one path
		 * as well (straight up). So the strategy here is to build on this knowledge as
		 * you move across and down memo. As you reach the last cell, it will eventually
		 * have the number of paths to the starting cell.
		 */
		for (int i = 0; i < m; i++) {
			memo[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			memo[0][j] = 1;
		}

		/*
		 * Traverse memo in a loop starting from memo[1][1], while filling in each cell
		 * using the information from cells directly above and to the left of the
		 * current cell. To see why this works - consider memo[0][0], which is
		 * originally 1. Now, if you're at memo[1][1], you can either move up a row and
		 * then follow the path left or move left a column and follow the path above.
		 * Therefore, you must sum memo[0][1] and memo[1][0] to get the total number of
		 * paths possible :
		 */
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
			}
		}
		/*
		 * The last cell of the game board holds the answer to the problem.
		 */
		return memo[m - 1][n - 1];
	}

	public static void main(String[] args) {
		int[][] a = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1 },
				{ 0, 1, 0, 1, 0 } };

		int[][] b = new int[][] { { 0, 1, 0, 0, 1 }, { 1, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 },
				{ 1, 0, 0, 0, 0 } };

		int[][] c = new int[][] { { 0, 1, 1, 0 }, { 1, 0, 0, 0 }, { 1, 0, 0, 1, }, { 0, 0, 1, 0 } };
		DFS dfs = new DFS(b);
		dfs.traverse();
	}
}
