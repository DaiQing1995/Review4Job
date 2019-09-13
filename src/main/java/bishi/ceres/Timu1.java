package bishi.ceres;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Timu1 {

	private static PriorityQueue<CNumber> numbers = new PriorityQueue<>();
	
	public static class CNumber implements Comparable<CNumber> {
		double val;

		public CNumber(double val) {
			super();
			this.val = val;
		}

		@Override
		public int compareTo(CNumber o) {
			if (this.val < o.val) {
				return -1;
			} else if (this.val == o.val) {
				return 0;
			} else {
				return 1;
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		double ans = 0.0, x;
		for (int i = 0; i < n; i++) {
			x = sc.nextDouble();
			numbers.add(new CNumber(x));
		}
		
		while(numbers.size() != 1) {
			CNumber num1 = numbers.poll();
			CNumber num2 = numbers.poll();
			numbers.add(new CNumber((num1.val + num2.val) / 2));
		}
		
		System.out.println(numbers.poll().val);
	}

}
