package com.amit.stack;

public class MyStack {
	private int top;
	private int[] array;

	public MyStack(int size) {
		top = -1;
		array = new int[size];
	}

	public void push(int data) {
		if (top == array.length - 1) {
			// stack full+---------------------
		} else {
			array[++top] = data;
		}
	}

	public int pop() {
		if (top == -1) {
			// stack empty
		}
		return array[top--];

	}

}
