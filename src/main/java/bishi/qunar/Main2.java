package bishi.qunar;

import java.util.Scanner;

/**
 * ��һ�⣬��Ŀ�о�������
 * �����ٷ�˵�и�Ϊ�Ӿ����С�����뻹δ�޸ġ�
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
