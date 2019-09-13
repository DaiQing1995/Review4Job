package bishi.dji;

import java.util.Scanner;

public class Main3 {

	static int[][] dp;
	public static void clear() {
		for (int i = 0;i < dp.length; ++ i) {
			for (int j = 0;j < dp[0].length; ++ j)
				dp[i][j] = 0;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line;
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			String[] split = line.trim().split(" ");
			int N = Integer.parseInt(split[0]);
			int T = Integer.parseInt(split[1]);
			int[] price = new int[N + 1];
			int[] satisfy = new int[N + 1];
			int[] countFood = new int[N + 1];
			
			int minPrice = Integer.MAX_VALUE - 1000;
			for (int i = 1;i <= N; ++ i) {
				line = sc.nextLine();
				split = line.trim().split(" "); 
				price[i] = Integer.parseInt(split[0]);
				satisfy[i] = Integer.parseInt(split[1]);
				countFood[i] = Integer.parseInt(split[2]);
				minPrice = Math.min(minPrice, price[i]);
			}
			if (minPrice > T)
				System.out.println(0);
			else {
				dp = new int[200][10000]; 
				for (int i = 1; i <= N; ++ i) {
					for (int j = 1; j <= T; ++ j) {
						if (price[i] < T) {
							dp[i][j] = dp[i][j - 1];
							dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
							for (int k = 1; k <= countFood[i]; ++ k) {
								if (j - price[i] * k >= 0) 
									dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - price[i] * k] + satisfy[i] * k);
							}
						}
					}
				}
				System.out.println(dp[N][T]);
				clear();
			}
		}
		sc.close();
	}
	
}
