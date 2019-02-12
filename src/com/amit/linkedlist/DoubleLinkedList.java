package com.amit.linkedlist;

class DNode {
	int data;
	DNode prev;
	DNode next;

	public DNode(int data) {
		super();
		this.data = data;
	}

}

public class DoubleLinkedList {

	DNode sortedInsert(DNode head, int data) {

		DNode n = new DNode(data);

		DNode p = head;
		DNode t = null;

		while (p != null && p.data <= data) {
			t = p;
			p = p.next;
		}
		if (t == null) {
			n.next = p;
			n.prev = null;
			head = n;
		} else {
			t.next = n;
			n.prev = t;
			n.next = p;
			/*
			 * if p == null, then there are two possibilities: 1. p has traversed beyond the
			 * end of the list (condition p.data <= data is not matched anywhere in the
			 * list.) 2. p has not traversed at all (empty list) So, make sure p is not null
			 * before assigning its previous node.
			 */
			if (p != null) {
				p.prev = n;
			}
		}

		return head;
	}

	public DNode insertAtPos(DNode head, int data, int pos) {
		DNode newNode = new DNode(data);
		// Null array
		if (head == null && pos == 1)
			head = newNode;
		else {
			DNode currentNode = head;
			DNode prevNode = head;
			int count = 1;
			// Traverse the list to the insert position
			while (currentNode != null) {
				if (count == pos)
					break;
				else {
					prevNode = currentNode;
					currentNode = currentNode.next;
					count++;
				}
			}
			// Do nothing if position not available
			if (count < pos)
				return head;
			// Insert at heading position
			else if (count == 1) {
				newNode.next = currentNode;
				currentNode.prev = newNode;
				head = newNode;
			}
			// Insert at middle position
			else if (currentNode != null) {
				newNode.next = currentNode;
				newNode.prev = prevNode;
				prevNode.next = newNode;
				currentNode.prev = newNode;
			}
			// Insert at ending position
			else {
				prevNode.next = newNode;
				newNode.prev = prevNode;
			}
		}
		return head;
	}

}
