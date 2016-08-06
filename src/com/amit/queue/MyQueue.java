package com.amit.queue;

public class MyQueue {

	private int array[];
	private int r;
	private int f;

	public MyQueue(int size) {
		r = -1;
		f = 0;
		array = new int[size];
	}

	public void push(int data) {
		if (r == array.length - 1) {
			System.out.println("Q is full");
			return;
		}
		array[++r] = data;
	}

	public int pop() {
		if (f > r) {
			System.out.println("Q is empty");
		} else {
			return array[f++];
		}

		return -1;
	}

	public void show() {
		for (int i = f; i <= r; i++) {
			System.out.print(array[i] + ",");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		MyQueue q = new MyQueue(3);
		q.push(1);
		q.push(2);
		q.push(3);
		// q.push(4);
		q.show();
		System.out.println("Pop: " + q.pop());
		q.show();
		System.out.println("Pop: " + q.pop());
		q.show();
		System.out.println("Pop: " + q.pop());
		q.show();
		// System.out.println("Pop: " + q.pop());
		// q.show();

	}

}
