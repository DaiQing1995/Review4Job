package bishi.qunar;

import java.util.Scanner;

/**
 * 中序和后序输出前序
 * @author DaiQing
 *
 */
public class Main {

	private static class Node {
		char val;
		Node left;
		Node right;
		public Node(char val) {
			this.val = val;
		}
	}

	static StringBuilder sBuilder = new StringBuilder();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[] order = scanner.nextLine().toCharArray();
		char[] last = scanner.nextLine().toCharArray();
		int highIndex = 0;
		int fstIndex = 0;
		Node root = null;
		while (fstIndex < order.length) {
			while (order[fstIndex] != last[highIndex])
				highIndex++;
			if (root == null) {
				root = getResult(order, last, fstIndex, highIndex, fstIndex, highIndex);
			} else {
				Node tmp = getResult(order, last, fstIndex, highIndex, fstIndex, highIndex);
				tmp.left = root;
				root = tmp;
			}
			fstIndex = highIndex + 1;
		}
		getFstOrder(root);
		System.out.println(sBuilder.toString());
		scanner.close();
	}

	private static Node getResult(char[] order, char[] last, int orderStart, int orderEnd, int lastStart, int lastEnd) {
		if (orderStart > orderEnd || lastStart > lastEnd)
			return null;
		Node curNode = new Node(order[orderStart]);
		if (order[orderStart] == last[lastStart]) {
			Node father = getResult(order, last, orderStart + 1, orderEnd, lastStart, lastEnd - 1);
			if (father == null)
				return curNode;
			father.left = curNode;
			return father;
		}else {
			Node rightChild = getResult(order, last, orderStart+ 1, orderEnd, lastStart, lastEnd - 1);
		    curNode.right = rightChild;
		    return curNode;
		}
	}
	
	private static void getFstOrder(Node root) {
		sBuilder.append(root.val);
		if (root.left != null) {
			getFstOrder(root.left);
		}
		if (root.right != null) {
			getFstOrder(root.right);
		}
	}
}
