package bishi;

import java.util.ArrayList;
import java.util.Collections;

public class Solution3 {

	private static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;

		}
	}

	TreeNode ans;
	boolean ansGet;

	public int midTraverse(TreeNode node, int times, int k) {
		if (ansGet)
			return (times + 1);
		if (node.left != null) {
			times = midTraverse(node.left, times, k);
		}
		times++;
		if (times == k) {
			ans = node;
			ansGet = true;
		}
		if (node.right != null) {
			times = midTraverse(node.right, times, k);
		}
		return times;
	}

	TreeNode KthNode(TreeNode pRoot, int k) {
		midTraverse(pRoot, 0, k);
		ansGet = false;
		return ans;
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(8);
		TreeNode node6 = new TreeNode(6);
		TreeNode node10 = new TreeNode(10);
		root.left = node6;
		root.right = node10;
		TreeNode node5 = new TreeNode(5);
		TreeNode node7 = new TreeNode(7);
		node6.left = node5;
		node6.right = node7;
		TreeNode node9 = new TreeNode(9);
		TreeNode node11 = new TreeNode(11);
		node10.left = node9;
		node10.right = node11;
		System.out.println(new Solution3().KthNode(root, 3).val);
		ArrayList<Integer> a;
	}
}