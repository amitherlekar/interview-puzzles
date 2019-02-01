package com.amit.tree;

import java.util.ArrayList;
import java.util.Stack;

//Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class ArrayToBST {

	public int getHeight(TreeNode head) {
		if (head == null)
			return 0;

		int leftHeight = getHeight(head.left);
		int rightHeight = getHeight(head.right);

		return Math.max(leftHeight, rightHeight) + 1;
	}

	public boolean isBalanced(TreeNode head) {
		if (head == null) {
			return true;
		}
		if (Math.abs((getHeight(head.left) - getHeight(head.right))) > 1) {
			return false;
		}

		return isBalanced(head.left) && isBalanced(head.right);
	}

	public void printInOrder(TreeNode head) {
		if (head == null) {
			return;
		}
		printInOrder(head.left);
		System.out.print(head.val + ", ");
		printInOrder(head.right);
	}

	public void printPreOrder(TreeNode head) {
		if (head == null) {
			return;
		}
		System.out.print(head.val + ", ");
		printPreOrder(head.left);
		printPreOrder(head.right);
	}

	public String printPostOrder(TreeNode head) {
		if (head == null) {
			return "";
		}

		return printPostOrder(head.left) + printPostOrder(head.right) + ", " + head.val;

	}

	public TreeNode insert(TreeNode root, int value) {

		if (root == null) {
			TreeNode newNode = new TreeNode(value);
			root = newNode;
		}

		if (value < root.val) {
			root.left = insert(root.left, value);
		} else if (value > root.val) {
			root.right = insert(root.right, value);
		}

		return root;

	}

	/**
	 * LCA - least common ancestor 4 / \ 2 7 / \ / 1 3 6 v1=1 and v2=7.
	 * 
	 * LCA of 1 and 7 is 4 (which is the root). Return a pointer to the root in this
	 * case.
	 * 
	 * @param root
	 * @param v1
	 * @param v2
	 * @return
	 */
	public TreeNode lca(TreeNode root, int v1, int v2) {

		if (v1 < root.val && v2 < root.val) {
			return lca(root.left, v1, v2);
		} else if (v1 > root.val && v2 > root.val) {
			return lca(root.right, v1, v2);
		}
		return root;
	}

	public TreeNode sortedArrayToBST(int[] num) {
		if (num.length == 0)
			return null;
		return sortedArrayToBST(num, 0, num.length - 1);
	}

	public TreeNode sortedArrayToBST(int[] array, int low, int high) {
		if (low > high)
			return null;

		int mid = (low + high) / 2;
		TreeNode root = new TreeNode(array[mid]);
		root.left = sortedArrayToBST(array, low, mid - 1);
		root.right = sortedArrayToBST(array, mid + 1, high);
		return root;
	}

	public void compareTrees(TreeNode t1, TreeNode t2) {
		if (t1 != null) {
			String postOrderStringT1 = printPostOrder(t1);
			System.out.println(postOrderStringT1);
		}

		if (t2 != null) {
			String postOrderStringT2 = printPostOrder(t2);
			System.out.println(postOrderStringT2);
		}

	}

	public int countNumberOfNodes(TreeNode root) {

		if (root == null) {
			return 0;
		}

		return countNumberOfNodes(root.left) + countNumberOfNodes(root.right) + 1;
	}

	public int countNumberOfLeafNodes(TreeNode root) {

		if (root == null) {
			return 0;
		} else if (root.left == null && root.right == null) {
			return 1;
		}

		return countNumberOfLeafNodes(root.left) + countNumberOfLeafNodes(root.right);
	}

	public boolean isStrictlyBinaryTree(TreeNode root) {
		int leafCount = countNumberOfLeafNodes(root);
		int nodeCount = countNumberOfNodes(root);
		return (2 * leafCount - 1 == nodeCount);
	}

	public int depthOfTree(TreeNode root) {
		int depth = 0;
		int levelLeft = 0;
		int levelRight = 0;

		if (root == null) {
			return 0;
		}

		levelLeft = depthOfTree(root.left);
		levelRight = depthOfTree(root.right);
		depth = Math.max(levelLeft, levelRight) + 1;

		return depth;
	}

	static boolean isBST(TreeNode root) {
		return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/*
	 * Returns true if the given tree is a BST and its values are >= min and <= max.
	 */
	static boolean isBSTUtil(TreeNode node, int min, int max) {
		/* an empty tree is BST */
		if (node == null)
			return true;

		/* false if this node violates the min/max constraints */
		if (node.val < min || node.val > max)
			return false;

		/*
		 * otherwise check the subtrees recursively tightening the min/max constraints
		 */
		// Allow only distinct values
		return (isBSTUtil(node.left, min, node.val - 1) && isBSTUtil(node.right, node.val + 1, max));
	}

	/**
	 * Method to iteratively traverse the tree in the preorder manner. Mark a node
	 * as visited by adding its data to a list - Arraylist <Integer> preorderedList.
	 * Return this list.
	 * 
	 * @param root
	 * @return
	 */
	public ArrayList<Integer> preorderItr(TreeNode root) {

		ArrayList<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();

		if (root == null) {
			return list;
		}
		TreeNode p = null;
		stack.push(root);

		while (!stack.isEmpty()) {
			p = stack.pop();
			list.add(p.val);
			if (p.right != null) {
				stack.push(p.right);
			}

			if (p.left != null) {
				stack.push(p.left);
			}
		}
		return list;

	}

	public static void main(String[] args) {
		int a[] = new int[] { 1, 2, 3, 4, 5 };
		int b[] = new int[] { 1, 2, 3, 4, 5 };

		ArrayToBST bst = new ArrayToBST();
		TreeNode t1 = bst.sortedArrayToBST(a);
		/*
		 * TreeNode t2 = bst.sortedArrayToBST(b);
		 * System.out.println("Post order of t2 = " + bst.printPostOrder(t2));
		 * System.out.println("Tree height = " + bst.getHeight(t1));
		 * System.out.println("Is tree balanced = " + bst.isBalanced(t1));
		 */
		System.out.println("Number of nodes in the tree is: " + bst.countNumberOfNodes(t1));
		System.out.println("Number of leaf nodes in the tree is: " + bst.countNumberOfLeafNodes(t1));
		System.out.println("Is T1 is a strictly binary tree?: " + bst.isStrictlyBinaryTree(t1));

		System.out.println("The depth of tree T1 is: " + (bst.depthOfTree(t1) - 1));
		// bst.compareTrees(t1, t2);
	}
}
