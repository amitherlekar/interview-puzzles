package com.amit.graph;

import java.util.Stack;

public class TSP {

	private Stack<Integer> stack;
	private boolean[] visited;
	private int[][] adjacencyMatrix;

	public TSP(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
		stack = new Stack<Integer>();
		visited = new boolean[adjacencyMatrix.length];
	}

	public void tsp() {

		visited[0] = true;
		stack.push(0);
		System.out.print((0 + 1) + " --> \t");
		int element = 0;

		while (!stack.isEmpty()) {
			element = stack.peek();
			int to = getAdjacentUnvisitedNodeOfMinWeight(element);
			if (to > 0) {
				visited[to] = true;
				stack.push(to);
				System.out.print((to + 1) + " --> \t");
			} else {
				stack.pop();
			}
		}

	}

	private int getAdjacentUnvisitedNodeOfMinWeight(int element) {
		int min = Integer.MAX_VALUE;
		int to = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (adjacencyMatrix[element][i] > 0 && !visited[i]) {
				if (adjacencyMatrix[element][i] < min) {
					min = adjacencyMatrix[element][i];
					to = i;
				}
			}
		}
		return to;
	}

	public static void main(String[] args) {
		int graph[][] = { { 000, 374, 200, 223, 108, 178, 252, 285, 240, 356 },
				{ 374, 000, 255, 166, 433, 199, 135, 95, 136, 017 },
				{ 200, 255, 000, 128, 277, 128, 180, 160, 131, 247 },
				{ 223, 166, 128, 000, 430, 47, 52, 84, 40, 155 },
				{ 108, 433, 277, 430, 000, 453, 478, 344, 389, 423 },
				{ 178, 199, 128, 47, 453, 000, 91, 110, 64, 181 },
				{ 252, 135, 180, 52, 478, 91, 000, 114, 83, 117 },
				{ 285, 95, 160, 84, 344, 110, 114, 000, 47, 78 },
				{ 240, 136, 131, 40, 389, 64, 83, 47, 000, 118 },
				{ 356, 17, 247, 155, 423, 181, 117, 78, 118, 000 } };
		TSP tsp = new TSP(graph);
		tsp.tsp();
		// Output: 1 --> 5 --> 3 --> 4 --> 9 --> 8 --> 10 --> 2 --> 7 --> 6
	}
}
