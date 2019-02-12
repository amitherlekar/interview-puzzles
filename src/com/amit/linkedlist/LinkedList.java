package com.amit.linkedlist;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LinkedList {

	public Node reverseByIteration(Node head) {

		Stack<Node> stack = new Stack<Node>();

		Node p = head;

		while (p != null) {
			stack.push(p);
			p = p.getNext();
		}

		Node newHead = stack.pop();
		p = newHead;
		while (!stack.isEmpty()) {
			Node n = stack.pop();
			p.setNext(n);
			p = p.getNext();
		}
		p.setNext(null);

		return newHead;

	}

	/*
	 * Given pointers to the head nodes of linked lists that merge together at some
	 * point, find the Node where the two lists merge. It is guaranteed that the two
	 * head Nodes will be different, and neither will be NULL. In the diagram below,
	 * the two lists converge at Node x:
	 * 
	 * [List #1]: a--->b--->c \ x--->y--->z--->NULL / [List #2]: p--->q
	 */
	String findMergeNode(Node a, Node b) {

		Node currentA = a;
		Node currentB = b;

		// Do till the two nodes are the same
		while (currentA != currentB) {
			// If you reached the end of one list start at the beginning of the
			// other one
			// currentA
			if (currentA.getNext() == null) {
				currentA = b;
			} else {
				currentA = currentA.getNext();
			}

			// currentB
			if (currentB.getNext() == null) {
				currentB = a;
			} else {
				currentB = currentB.getNext();
			}
		}
		return currentB.getData();

	}

	boolean hasCycle(Node head) {
		Node p = head;
		Node t = head;

		while (p != null) {
			if (p.getNext() == t)
				return true;

			p = p.getNext().getNext();
			t = t.getNext();
		}
		return false;
	}

	public Node removeDuplicates(Node head) {

		if (head == null)
			return null;

		Set<String> set = new HashSet<>();
		set.add(head.data);

		Node p = head.next;
		Node q = head;

		while (p != null) {

			if (!set.add(p.data)) {
				q.next = p.next;
			}
			q = p;
			p = p.next;
		}

		return head;

	}

	public Boolean isListPalindrome(Node head) {
		if (head == null || head.next == null)
			return true;

		// Go to the middle node
		int mid = 0, count = 0, length = 0;
		Node current = head, midNode = null;
		while (current != null) {
			current = current.next;
			length++;
		}
		mid = length / 2;
		current = head;
		while (current != null) {
			if (count == mid)
				break;
			current = current.next;
			count++;
		}
		midNode = current;

		// Reverse
		Node p1 = midNode;
		Node p2 = p1.next;
		while (p1 != null && p2 != null) {
			Node temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}
		midNode.next = null;

		// Compare
		Node cur = (p2 == null ? p1 : p2);
		Node com = head;
		while (cur != null) {
			if (cur.data != com.data)
				return false;
			cur = cur.next;
			com = com.next;
		}

		return true;
	}

	/**
	 * Get Nth element from the end in a linked list of strings
	 *
	 * @param head
	 *            head node of the linked list
	 * @param n
	 *            - position from the end. zero nased.
	 * @return the node data
	 */
	public String getNodeOfNthPostionFromEnd(Node head, int n) {
		Node p = head;
		Node t = head;
		int index = 0;

		while (p != null && p.getNext() != null) {
			if (index == n)
				break;
			p = p.getNext();
			index++;
		}
		while (p != null && p.getNext() != null) {
			t = t.getNext();
			p = p.getNext();

		}
		return t.getData();
	}

	/**
	 * Merges two sorted lists
	 *
	 * @param a
	 * @param b
	 * @return the head node
	 */
	public Node mergeListsUsingRecursion(Node a, Node b) {
		if (a == null) {
			return b;
		} else if (b == null) {
			return a;
		}

		if (a.getData().compareTo(b.getData()) < 0) {
			/*
			 * if (a.data < b.data) a.next = MergeLists(a.next, b);
			 */
			a.setNext(mergeListsUsingRecursion(a.getNext(), b));
			return a;
		} else {
			/* b.next = MergeLists(a, b.next); */
			b.setNext(mergeListsUsingRecursion(a, b.getNext()));
			return b;
		}

	}

	/**
	 * Compare two linked lists A and B Return 1 if they are identical and 0 if they
	 * are not.
	 */
	public int compareLists(Node headA, Node headB) {
		if (headA == null && headB == null) {
			return 1;
		} else if (headA == null || headB == null) {
			return 0;
		}

		if (headA.getData().equals(headB.getData())) {
			return compareLists(headA.getNext(), headB.getNext());
		} else {
			return 0;
		}
	}

	public Node createList(String[] array) {
		Node head = null;
		Node p = null;
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				head = new Node();
				head.setData(array[i]);
				p = head;
			} else {
				Node t = new Node();
				t.setData(array[i]);
				p.setNext(t);
				p = p.getNext();
			}
		}
		return head;
	}

	public Node getNode(Node head, int pos) {
		Node p = head;
		int index = 1;

		while (p != null) {
			if (index == pos) {
				return p;
			}
			index++;
			p = p.getNext();
		}

		return null;
	}

	public Node createCircularList(String[] array) {
		Node head = null;
		Node p = null;
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				head = new Node();
				head.setData(array[i]);
				p = head;
			} else {
				Node t = new Node();
				t.setData(array[i]);
				p.setNext(t);
				p = p.getNext();
			}
		}
		p.setNext(head);
		return head;
	}

	public Node isCircularList(Node head) {
		Node slow = head;
		Node fast = head;

		// Find meeting point
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (slow == fast) {
				break;
			}
		}

		// Error check - there is no meeting point, and therefore no loop
		if (fast == null || fast.getNext() == null) {
			return null;
		}

		/*
		 * Move slow to Head. Keep fast at Meeting Point. Each are k steps /* from the
		 * Loop Start. If they move at the same pace, they must meet at Loop Start.
		 */
		slow = head;
		while (slow != fast) {
			slow = slow.getNext();
			fast = fast.getNext();
		}

		// Both now point to the start of the loop.
		return fast;
	}

	public void iterate(Node head) {
		Node p = head;
		while (p != null) {
			System.out.print(p.getData() + ", ");
			p = p.getNext();
		}

		System.out.println();
	}

	public void iterateFromPosition(Node head, int pos) {
		int index = 1;
		Node p = head;
		while (p != null) {
			if (index >= pos) {
				System.out.print(p.getData() + ", ");
			}
			p = p.getNext();
			index++;
		}

		System.out.println();
	}

	public Node deleteNodeAt(Node head, int pos) {
		Node p = head;
		Node t = null;
		boolean matchFound = false;
		int index = 1;

		while (p != null && p.getNext() != null) {

			if (pos == 1) {
				head = head.getNext();
				return head;
			} else {
				t = p;
				p = p.getNext();
				index++;
			}
			if (index == pos) {
				t.setNext(p.getNext());
				matchFound = true;
				break;
			}
		}

		if (!matchFound) {
			System.out.println("Position not found");
			return null;
		}
		return head;
	}

	public static void main(String[] args) {

		String[] array = new String[] { "a", "b", "c" };
		String[] array1 = new String[] { "d", "e", "f", };
		// String[] array = new String[] { "a" };
		LinkedList list = new LinkedList();
		// Node head = list.createCircularList(array);
		Node head1 = list.createList(array);
		Node head2 = list.createList(array1);
		Node reversedHead = list.reverseByIteration(head1);

		// list.isCircularList(head);
		// Node n1 = list.removeDuplicates(head1);
		list.iterate(reversedHead);
		// list.iterateFromPosition(head, 3);
		// Node n = list.getNode(head, 4);
		// Node p = list.isCircularList(head);
		// System.out.println(p.getData());

		/*
		 * head = list.deleteNodeAt(head, 1); list.iterate(head);
		 */

	}
}