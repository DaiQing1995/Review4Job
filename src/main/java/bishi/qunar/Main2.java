package bishi.qunar;

import java.util.Scanner;

/**
 * 第一题，题目感觉有问题
 * 后来官方说有改为子矩阵大小，代码还未修改。
 * @author DaiQing
 *
 */
public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[][] data = new int[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				data[i][j] = scanner.nextInt();
			}
		}
		int max = 0;
		for (int i = 0; i < N; ++i) {
			int tmp = 0;
			for (int j = 0; j < N; ++j) {
				tmp += data[i][j];
				if (tmp < 0)
					tmp = 0;
				if (tmp > max)
					max = tmp;
			}
		}
		for (int i = 0; i < N; ++i) {
			int tmp = 0;
			for (int j = 0; j < N; ++j) {
				tmp += data[j][i];
				if (tmp < 0)
					tmp = 0;
				if (tmp > max)
					max = tmp;
			}
		}
		System.out.println(max);
		scanner.close();
	}
}
