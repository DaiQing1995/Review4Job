package bishi.shunfeng;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n == 0) {
			System.out.println(0);
			return;
		}
		int[] number = new int[n];
		for (int i = 0; i < n; ++i) {
			number[i] = sc.nextInt();
		}
		int[] dp = new int[n];
		dp[0] = 1;
		int max = 1;
		for (int i = 1; i < n; ++i) {
			int tmpIndex = i - 1;
			int curMaxDp = 0;
			while(tmpIndex >= 0) {
				while (tmpIndex >= 0 && number[tmpIndex] > number[i])
					tmpIndex--;
				if (tmpIndex == -1) {
					curMaxDp = Math.max(1, curMaxDp);
					break;
				}
				if (curMaxDp < dp[tmpIndex] + 1)
					curMaxDp = dp[tmpIndex] + 1;
				tmpIndex --;
			}
			dp[i] = curMaxDp;
			if (dp[i] > max)
				max = dp[i];
		}
		System.out.println(max);
		sc.close();
	}
}
