package bishi.pinduoduo;

import java.util.Scanner;

public class Timu3 {

	private static int N;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = Integer.parseInt(scanner.nextLine());
		int[] data = new int[N];
		double ans = 0;
		int maxIndex = -1;
		int max = 0;
		for (int i = 0; i < N; ++i) {
			data[i] = scanner.nextInt();
			if (max < data[i]) {
				max = data[i];
				maxIndex = i;
			}
		}
		for (int i = 0; i < data.length; ++i) {
			if (i == maxIndex)
				continue;
			for (int j = 1; j <= data[i]; ++ j)
				ans += max + j;
		}
		for (int i = 0; i < data.length; ++ i) {
			ans /= data[i];
		}
		System.out.format("%.2f", ans);
		scanner.close();
	}

}