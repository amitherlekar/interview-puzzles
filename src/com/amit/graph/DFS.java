package com.amit.graph;

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

	public static void main(String[] args) {
		int[][] a = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 } };

		int[][] b = new int[][] { { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
		DFS dfs = new DFS(a);
		dfs.traverse();
	}
}
