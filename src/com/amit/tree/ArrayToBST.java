package com.amit.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
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

	/**
	 * Given the root of a Binary Tree and an integer that represents the data value
	 * of a TreeNode present in the tree, write a method - pathLengthFromRoot that
	 * returns the distance between the root and that node. You can assume that the
	 * given key exists in the tree. The distance is defined as the minimum number
	 * of nodes that must be traversed to reach the target node.
	 * 
	 * @param root
	 * @param n1
	 * @return
	 */
	public int pathLengthFromRoot(TreeNode root, int n1) {

		if (root == null)
			return 0;
		int out = 0;

		if ((root.val == n1) || (out = pathLengthFromRoot(root.left, n1)) > 0
				|| (out = pathLengthFromRoot(root.right, n1)) > 0) {
			return out + 1;
		}

		return out;
	}

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
	 * Validate BST iteratively
	 * 
	 * @param root
	 * @return
	 */
	public static boolean validateBSTItr(TreeNode root) {

		class TreeBoundaryNode {
			TreeNode treeNode;
			int leftBoundary;
			int rightBoundary;

			TreeBoundaryNode(TreeNode treeNode, int leftBoundary, int rightBoundary) {
				this.treeNode = treeNode;
				this.leftBoundary = leftBoundary;
				this.rightBoundary = rightBoundary;
			}
		}

		if (root == null || (root.left == null && root.right == null))
			return true;

		Queue<TreeBoundaryNode> q = new LinkedList<>();
		q.add(new TreeBoundaryNode(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
		while (!q.isEmpty()) {
			TreeBoundaryNode tbNode = q.poll();
			TreeNode t = tbNode.treeNode;
			if ((t.val <= tbNode.leftBoundary) || (t.val >= tbNode.rightBoundary))
				return false;
			if (t.left != null) {
				q.add(new TreeBoundaryNode(t.left, tbNode.leftBoundary, t.val));
			}
			if (t.right != null) {
				q.add(new TreeBoundaryNode(t.right, t.val, tbNode.rightBoundary));
			}
		}

		return true;
	}

	public ArrayList<ArrayList<Integer>> printLevelByLevel(TreeNode root) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> level = new ArrayList<Integer>();

		if (root == null)
			return list;
		Queue<TreeNode> currLvl = new LinkedList<TreeNode>();
		Queue<TreeNode> nextLvl = new LinkedList<TreeNode>();
		currLvl.add(root);

		while (!currLvl.isEmpty()) {
			TreeNode curr = currLvl.remove();
			if (curr != null) {
				level.add(curr.val);
				nextLvl.add(curr.left);
				nextLvl.add(curr.right);
			}
			if (currLvl.isEmpty()) {
				if (!level.isEmpty())
					list.add(level);
				level = new ArrayList<Integer>();
				while (!nextLvl.isEmpty()) {
					currLvl.add(nextLvl.remove());
				}
			}
		}
		return list;
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

	public boolean isIdentical(TreeNode root1, TreeNode root2) {

		if (root1 == null && root2 == null)
			return true;

		if (root1 == null || root2 == null)
			return false;

		return (root1.val == root2.val && isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right));

	}

	public TreeNode mirror(TreeNode root) {

		if (root == null) {
			return null;
		}

		TreeNode root1 = new TreeNode(root.val);
		root1.left = mirror(root.right);
		root1.right = mirror(root.left);

		return root1;

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

	/**
	 * Given a binary tree, write a method to perform a levelorder traversal and
	 * return an ArrayList of integers containing the data of the visited nodes in
	 * the correct order.
	 * 
	 * @param node
	 * @param min
	 * @param max
	 * @return
	 */
	public ArrayList<Integer> levelorder(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();

		if (root == null) {
			return list;
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			TreeNode p = q.remove();

			list.add(p.val);

			if (p.left != null)
				q.add(p.left);
			if (p.right != null)
				q.add(p.right);
		}

		return list;

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

	public TreeNode findMax(TreeNode root) {

		if (root == null) {
			return null;
		}

		if (root.right == null)
			return root;

		TreeNode p = root;

		while (p != null && p.right != null) {
			p = p.right;
		}

		if (p != null)
			return p.right;

		return null;
	}

	public TreeNode findKthSmallest(TreeNode root, int k) {
		if (root == null)
			return null;
		int leftSize = 0;
		if (root.left != null) {
			leftSize = countNumberOfNodes(root.left);
		}
		if (leftSize + 1 == k) {
			return root;
		} else if (k <= leftSize) {
			return findKthSmallest(root.left, k);
		} else {
			return findKthSmallest(root.right, k - leftSize - 1);// find k-leftSize-1 smallest in right subtree.
		}
	}

	public TreeNode findKthLargest(TreeNode root, int k) {
		if (root == null)
			return null;
		int rightSize = 0;
		if (root.right != null) {
			rightSize = countNumberOfNodes(root.right);
		}
		if (rightSize + 1 == k) {
			return root;
		} else if (k <= rightSize) {
			return findKthLargest(root.right, k);
		} else {
			return findKthLargest(root.left, k - rightSize - 1);
		}
	}

	public int[] diameterAndHeight(TreeNode root) {
		int heightDiameter[] = { 0, 0 }; // initialize the diameter and height
		if (root != null) {
			int[] leftResult = diameterAndHeight(root.left);
			int[] rightResult = diameterAndHeight(root.right);
			int height = Math.max(leftResult[1], rightResult[1]) + 1;
			int leftDiameter = leftResult[0];
			int rightDiameter = rightResult[0];
			int rootDiameter = leftResult[1] + rightResult[1] + 1;
			int finalDiameter = Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
			heightDiameter[0] = finalDiameter;
			heightDiameter[1] = height;
		}
		return heightDiameter;
	}

	/**
	 * Given a BST, write a function to return its diameter. The diameter of a
	 * Binary Tree is defined as the "Number of nodes on the longest path between
	 * two leaf nodes".
	 * 
	 * @param root
	 * @return
	 */
	public int diameter(TreeNode root) {
		int[] result = diameterAndHeight(root);
		return result[0];
	}

	public static int maxSumPath(TreeNode root) {
		int[] maxRecursiveHolder = new int[1];
		maxSumPathMain(root, maxRecursiveHolder);
		return maxRecursiveHolder[0];
	}

	public static int maxSumPathMain(TreeNode root, int[] maxRecursiveHolder) {
		if (root == null)
			return 0;
		int leftSum = maxSumPathMain(root.left, maxRecursiveHolder);
		int rightSum = maxSumPathMain(root.right, maxRecursiveHolder);
		// Get the max path sum up to this node, including this node's value
		int nodeCumVal = Math.max(root.val + leftSum, root.val + rightSum);
		// Check and update the max holder
		maxRecursiveHolder[0] = Math.max(maxRecursiveHolder[0], leftSum + root.val + rightSum);
		return nodeCumVal;
	}

	public String serializeTree(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serializeTreeHelper(root, sb);
		if (sb.length() > 0)
			sb.deleteCharAt(0);
		return sb.toString();
	}

	private StringBuilder serializeTreeHelper(TreeNode t, StringBuilder sb) {
		if (t == null)
			sb.append(",null");
		else {
			sb.append("," + t.val);
			serializeTreeHelper(t.left, sb);
			serializeTreeHelper(t.right, sb);
		}
		return sb;
	}

	public TreeNode restoreTree(String str) {
		String[] nodesSplit = str.split(",");
		LinkedList<String> nodesList = new LinkedList<>(Arrays.asList(nodesSplit));
		return restoreTreeHelper(nodesList);
	}

	public TreeNode restoreTreeHelper(LinkedList<String> nodes) {
		String nodeDataStr = nodes.remove();
		if (nodeDataStr.equals("null"))
			return null;
		TreeNode t = new TreeNode(Integer.valueOf(nodeDataStr));
		t.left = restoreTreeHelper(nodes);
		t.right = restoreTreeHelper(nodes);
		return t;
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

		System.out.println("The max node of tree T1 is: " + bst.findMax(t1).val);
		// bst.compareTrees(t1, t2);
	}

	Comparator<Integer> c = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			return 0;
		}
	};
}
