package com.amit.linkedlist;

public class LinkedListQueue {
	private Node head;
	private int size;
	private int f;
	private int r;

	public LinkedListQueue(int size) {
		this.size = size;
		r = -1;
		f = 0;
	}

	public void push(String data) {
		if (r == size - 1) {
			System.out.println("Q is full.");
			return;
		}
		if (head == null) {
			head = new Node();
			head.setData(data);
		} else {
			Node p = head;
			int index = 0;
			while (true) {
				if (index == r) {
					break;
				}
				p = p.getNext();
				index++;
			}
			Node t = new Node();
			t.setData(data);
			p.setNext(t);
		}
		r++;
	}

	public String pop() {
		if (f > r) {
			System.out.println("Q is empty");
			return null;
		}
		Node p = head;
		head = head.getNext();
		p.setNext(null);
		f++;
		return p.getData();
	}

	public void show() {

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

		LinkedListQueue list = new LinkedListQueue(3);
		list.push("1");
		list.push("2");
		list.push("3");
		// list.push("4");
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
