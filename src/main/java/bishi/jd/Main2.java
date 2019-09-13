package bishi.jd;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		PriorityQueue<Integer> pqSmall = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 == o2)
					return 0;
				if (o1 > o2)
					return 1;
				return -1;
			}
		});
		int[] data = new int[n];
		for (int i = 0; i < n; ++ i) {
			int curData = sc.nextInt();
			data[i] = curData;
			pqSmall.add(curData);
		}
		int ans = 0;
		int curGroupMaxVal = Integer.MIN_VALUE;
		for (int i = 0; i < data.length; ++ i) {
			curGroupMaxVal = data[i] > curGroupMaxVal ? data[i] : curGroupMaxVal;
			pqSmall.remove(data[i]);
			if (!pqSmall.isEmpty() && pqSmall.peek() >= curGroupMaxVal) {
				ans ++;
				curGroupMaxVal = Integer.MIN_VALUE;
			}
		}
		System.out.println(ans + 1);
		sc.close();
	}
}
/**
10
69079936 236011312 77957850 653604087 443890802 277126428 755625552 768751840 993860213 882053548
6
 * 
 */

