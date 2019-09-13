package bishi.netease;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {

	static class Num{
		int val;
		Num left;
		Num right;
		public Num(int val) {
			this.val = val;
		}
		public boolean checkAvailable() {
			if (left.val + right.val <= val)
				return false;
			else
				return true;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		PriorityQueue<Integer> pQueue = new PriorityQueue<>();
		for (int i = 0;i < n; ++ i) {
			int sum = scanner.nextInt();
			int[] num = new int[sum];
			for (int j = 0;j < sum; ++ j) {
				num[j] = scanner.nextInt();
				pQueue.add(num[j]);
			}
			System.out.println(check(num, pQueue) == true ? "Yes" : "No");
			pQueue.clear();
		}
		scanner.close();
	}

	private static boolean check(int[] num, PriorityQueue<Integer> pQueue) {
		Num root = new Num(pQueue.poll());
		root.left = new Num(pQueue.poll());
		root.left.right = root;
		root.right = new Num(pQueue.poll());
		root.right.left = root;
		Num lastLeft = root.left;
		Num lastRight = root.right;
		
		while(pQueue.size() > 0) {
			Num leftNum = new Num(pQueue.poll());
			lastLeft.left = leftNum;
			leftNum.right = lastLeft;
			lastLeft = lastLeft.left;
			if (pQueue.size() == 0)
				break;
			Num rightNum = new Num(pQueue.poll());
			lastRight.right = rightNum;
			rightNum.left = lastRight;
			lastRight = lastRight.right;
		}
		
		lastRight.right = lastLeft;
		lastLeft.left = lastRight;
		Num curNode = root.left;
		while(curNode != root) {
			if (curNode.checkAvailable() == false)
				return false;
			curNode = curNode.left;
		}
		return true;
	}
}
