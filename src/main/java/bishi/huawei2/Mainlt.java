package bishi.huawei2;

import java.util.Scanner;

public class Mainlt {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int[] fst = new int[n];
		int[] snd = new int[n];
		for (int i = 0; i < n; ++i) {
			fst[i] = sc.nextInt();
		}
		for (int i = 0; i < n; ++i) {
			snd[i] = sc.nextInt();
		}
		int[][] dp = new int[n][n];
		boolean equ = false;
		for (int i = 0; i < n; ++i) {
			if (fst[0] == snd[i]) {
				equ = true;
			}
			dp[0][i] = equ ? 1 : 0;
		}
		equ = false;
		for (int i = 0; i < n; ++i) {
			if (fst[i] == snd[0]) {
				equ = true;
			}
			dp[i][0] = equ ? 1 : 0;
		}
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				if (fst[i] == snd[j]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
			}
		}
		System.out.println(n - dp[n - 1][n - 1]);
		sc.close();
	}
}

/*
 * 
 * for (int i = 0; i < n; ++i) { for (int j = 0; j < n; ++j) {
 * System.out.print(dp[i][j] + " "); } System.out.println(); }
 */
