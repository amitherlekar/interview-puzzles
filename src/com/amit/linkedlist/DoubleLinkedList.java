package com.amit.linkedlist;

class DNode {
	int data;
	DNode prev;
	DNode next;

}

public class DoubleLinkedList {

	DNode sortedInsert(DNode head, int data) {

		DNode n = new DNode();
		n.data = data;

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
			 * if p == null, then there are two possibilities: 1. p has
			 * traversed beyond the end of the list (condition p.data <= data is
			 * not matched anywhere in the list.) 2. p has not traversed at all
			 * (empty list) So, make sure p is not null before assigning its
			 * previous node.
			 */
			if (p != null) {
				p.prev = n;
			}
		}

		return head;
	}

}
