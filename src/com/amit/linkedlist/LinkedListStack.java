package com.amit.linkedlist;

public class LinkedListStack {
	private Node head;
	private int size;
	private int top;

	public LinkedListStack(int size) {
		this.size = size;
		top = -1;
	}

	public void push(String data) {
		if (top == size - 1) {
			System.out.println("Stack is full.");
			return;
		}
		if (head == null) {
			head = new Node();
			head.setData(data);
		} else {
			Node p = head;
			int index = 0;
			while (true) {
				if (index == top) {
					break;
				}
				p = p.getNext();
				index++;
			}
			Node t = new Node();
			t.setData(data);
			p.setNext(t);
		}
		top++;
	}

	public String pop() {
		if (top == -1) {
			System.out.println("Stack is empty");
			return null;
		}
		Node p = head;
		Node t = head;
		int index = 0;
		while (true) {
			if (index == top) {
				break;
			}
			t = p;
			p = p.getNext();
			index++;
		}
		t.setNext(null);
		top--;
		return p.getData();
	}

	public void show() {
		if (top == -1) {
			System.out.println("Stack is empty");
			return;
		}
		Node p = head;
		while (true) {
			if (p == null) {
				break;
			}
			System.out.print(p.getData() + ", ");
			p = p.getNext();
		}
		System.out.println();
	}

	public static void main(String[] args) {

		LinkedListStack list = new LinkedListStack(3);
		list.push("1");
		list.push("2");
		list.push("3");
		list.push("4");
		list.show();
		System.out.println("Pop: " + list.pop());
		list.show();
		System.out.println("Pop: " + list.pop());
		list.show();
		System.out.println("Pop: " + list.pop());
		list.show();
		System.out.println("Pop: " + list.pop());
		list.show();
	}
}
