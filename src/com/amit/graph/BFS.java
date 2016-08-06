package com.amit.graph;

import java.util.PriorityQueue;
import java.util.Queue;

public class BFS {

	private int[][] graph;
	private boolean visited[];

	public BFS(int[][] graph) {
		if (graph == null || graph.length == 0) {
			throw new IllegalArgumentException("Graph cannot be null");
		}
		this.graph = graph;
		visited = new boolean[graph.length];
	}

	public void traverse() {
		Queue<Integer> q = new PriorityQueue<Integer>();
		q.add(0);
		visited[0] = true;
		System.out.print(0 + ", ");

		while (!q.isEmpty()) {
			int node = q.peek();
			int child = getUnvisitedNode(node);

			if (child > 0) {
				visited[child] = true;
				q.add(child);
				System.out.print(child + ", ");
			} else {
				q.remove();
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
		int[][] a = new int[][] { 
				{ 0, 1, 1, 1, 0 }, 
				{ 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0 }, 
				{ 1, 0, 0, 0, 1 }, 
				{ 0, 1, 0, 1, 0 } };

		int[][] b = new int[][] { { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
		BFS dfs = new BFS(a);
		dfs.traverse();
	}
}
