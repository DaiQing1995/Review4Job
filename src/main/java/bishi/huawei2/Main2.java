package bishi.huawei2;

import java.util.*;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double price = sc.nextDouble();
		double minDeviatePrice = 1000.0;
		int M = 1, N = 2;
		for (int i = 1; i < 10000; ++i) {
			double curPrice = price * (double) i;
			double tmpdeviate = Math.abs((int) curPrice - curPrice) / i;
			boolean flag = false;
			if (Math.abs((int) curPrice + 1 - curPrice) / i < tmpdeviate) {
				flag = true;
				tmpdeviate = Math.abs((int) curPrice + 1 - curPrice) / i; 
			}
					
			if (tmpdeviate < (minDeviatePrice - 0.0000000001)) {
				M = (int) curPrice;
				if (flag)
					M ++;
				minDeviatePrice = tmpdeviate;
				N = i;
			}
		}
		System.out.println(M + " " + N);
		sc.close();
	}
}
