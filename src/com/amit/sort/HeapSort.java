package com.amit.sort;

public class HeapSort {

	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public void print(int[] a) {
		System.out.print(" [ ");
		for (int i = 0; i < a.length; i++) {
			if (i > 0)
				System.out.print(", ");
			System.out.print(a[i]);
		}
		System.out.println(" ]");
	}

	public void sort(int[] a) {

		heapify(a);
		System.out.print("After heapify: ");
		print(a);

		int end = a.length - 1;

		while (end > 0) {
			swap(a, end, 0);
			end--;
			siftDown(a, 0, end);
		}
	}

	private void heapify(int[] a) {
		int start = parent(a.length - 1);

		while (start >= 0) {
			siftDown(a, start, a.length - 1);
			start--;
		}
	}

	private void siftDown(int[] a, int start, int end) {
		int root = start;
		int child = 0;
		int swap = 0;

		while (leftChild(root) <= end) {
			swap = root;

			// check the left child
			child = leftChild(root);
			if (a[child] > a[swap]) {
				swap = child;
			}
			// check the right child
			child = rightChild(root);
			if (child <= end && a[child] > a[swap]) {
				swap = child;
			}
			if (swap == root) {
				break;
			} else {
				swap(a, root, swap);
				root = swap;
			}
		}
	}

	private int leftChild(int i) {
		return (2 * i) + 1;
	}

	private int rightChild(int i) {
		return (2 * i) + 2;
	}

	private int parent(int i) {
		return Math.floorDiv(i - 1, 2);
	}

	public static void main(String[] args) {
		HeapSort h = new HeapSort();
		int[] a = new int[] { 88, 12, 9, 4, 8, 7, 6, 3, 1 };
		System.out.print("Input: ");
		h.print(a);
		h.sort(a);
		System.out.print("After sort: ");
		h.print(a);
	}

}
