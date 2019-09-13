package bishi.yuanfudao;

import java.util.Scanner;

public class Main {

	final static int[] moveX = {1 ,-1 ,0 ,0};
	final static int[] moveY = {0 ,0 ,1 ,-1}; 
	
	static int[][] value;
	static int[][][] dp;

	static int M;
	static int N;
	static int K;

	private static int dfs(int x, int y, int k) {
		if (dp[x][y][k] != -1) return dp[x][y][k];
		int ans = 1;
		for (int i = 0;i < 4; ++ i) {
			int nx = moveX[i] + x;
			int ny = moveY[i] + y;
			if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
				if (value[nx][ny] <= value[x][y] && k > 0) {
					ans = Math.max(ans, 1 + dfs(nx, ny, k - 1));
				}else if (value[nx][ny] > value[x][y]) {
					ans = Math.max(ans, 1 + dfs(nx, ny, k));
				}
			}
		}
		return ans;
	}
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		M = scanner.nextInt();
		N = scanner.nextInt();
		K = scanner.nextInt();
		value = new int[M][N];
		dp = new int[M][N][K + 1];
		for (int i = 0; i < M; ++i) {
			for (int j = 0; j < N; ++j) {
				value[i][j] = scanner.nextInt();
				for(int k = 0; k <= K; ++ k)
					dp[i][j][k] = -1;
			}
		}
		
		int ans = 0;
		for (int i = 0; i < M; ++ i) {
			for (int j = 0;j < N; ++ j) {
				for (int k = 0; k <= K; ++ k) {
					ans = Math.max(ans, dfs(i, j, k));
				}
			}
		}
		System.out.println(ans);
		scanner.close();
	}

}
