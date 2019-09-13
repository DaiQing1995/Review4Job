package bishi.pinduoduo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static int N;
	private static int M;
	private static int K;

	private static int findKthNumber(int m, int n, int k) {
	    int lo = 1, hi = m * n + 1;
	    int mid, count;
	    while (lo < hi) {
	        mid = lo + (hi - lo) / 2;
	        count = 0;
	        for (int i = 1; i <= m; i++) {
	            count += (mid/i > n ? n : mid/i);
	        }
	        if (count >= k) hi = mid;
	        else lo = mid + 1;
	    }
	    return lo;
	}
	
	public static void main(String[] args) {
		System.out.println(findKthNumber(3, 2, 2));
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		K = scanner.nextInt();
		int[] data = new int[2];
		data[0] = Math.max(N, M);
		data[1] = Math.min(N, M);
		int ans = 0;
		Queue<Integer> fstQueue = new LinkedList<>();
		Queue<Integer> sndQueue = new LinkedList<>();
		boolean val1Flag = false;
		boolean val2Flag = false;
       		while (K > 0) {
			K--;
			int val1 = 0;
			val1 = (data[0] - 1) * data[1];
			int val2 = 0;
			val2 = (data[1] - 1) * data[0];
			int val3 = 0;
			int val4 = 0;
			if (!val1Flag && !fstQueue.isEmpty())
				val3 = fstQueue.peek() * data[1];
			if (!val2Flag && !sndQueue.isEmpty())
				val4 = sndQueue.peek() * data[0];
			int max = Math.max(Math.max(val1, val2), Math.max(val3, val4));
			if (max == val1) {
				if (data[1] > 1) {
					fstQueue.add(data[0]);
					val1Flag = true;
				}
				val2Flag = false;
				data[0]--;
			} else if (max == val2) {
				if (data[0] > 1) {
					sndQueue.add(data[1]);
					val2Flag = true;
				}
				val1Flag = false;
				data[1]--;
			} else if (max == val3) {
				if (data[1] > 1)
					data[1]--;
				else
					fstQueue.poll();
				val1Flag = false;
				val2Flag = false;
			} else if (max == val4) {
				if (data[0] > 1)
					data[0]--;
				else
					sndQueue.poll();
				val1Flag = false;
				val2Flag = false;
			}
			ans = max;
			System.out.println(K + ":" + ans);
		}
		System.out.println(ans);
		scanner.close();
	}

}
/**
public int findKthNumber(int m, int n, int k) {
    int lo = 1, hi = m * n + 1;
    int mid, count;
    while (lo < hi) {
        mid = lo + (hi - lo) / 2;
        count = 0;
        for (int i = 1; i <= m; i++) {
            count += (mid/i > n ? n : mid/i);
        }
        if (count >= k) hi = mid;
        else lo = mid + 1;
    }
    return lo;
}
作者：caipengbo
链接：https://leetcode-cn.com/problems/kth-smallest-number-in-multiplication-table/solution/chao-ji-jian-dan-de-javaer-fen-fa-by-caipengbo/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/
