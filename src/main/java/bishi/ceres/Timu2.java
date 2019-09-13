package bishi.ceres;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Timu2 {

	private static long[][] matrix;

	public static class Val implements Comparable<Val> {
		int row;
		int col;
		long val;

		public Val(int row, int col, long val) {
			super();
			this.row = row;
			this.col = col;
			this.val = val;
		}

		@Override
		public int compareTo(Val o) {
			if (this.val < o.val) {
				return 1;
			} else if (this.val == o.val) {
				return 0;
			} else {
				return -1;
			}

		}

	}

	private static PriorityQueue<Val> vals = new PriorityQueue<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		matrix = new long[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; ++j) {
				matrix[i][j] = sc.nextInt();
				vals.add(new Val(i, j, matrix[i][j]));
			}
		}

		long result = Integer.MIN_VALUE / 2;
		ArrayList<Val> tmpList = new ArrayList<>();
		int count = 0;
		while(count < n) {
			Val poll = vals.poll();
			int tabuCol = poll.col;
			int tabuRow = poll.row;
			Val tmp;
			while (true) {
				tmp = vals.poll();
				tmpList.add(tmp);
				if (tmp.col != tabuCol && tmp.row != tabuRow) {
					if (tmp.val * poll.val > result)
						result = tmp.val * poll.val;
					break;
				}
			}
			vals.addAll(tmpList);
			tmpList.clear();
			count ++;
		}
		System.out.println(result);
	}

}
