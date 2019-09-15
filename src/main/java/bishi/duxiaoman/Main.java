package bishi.duxiaoman;

import java.util.Scanner;

public class Main {

	static int N, A, B, C;
	static int[] Arr;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		A = scanner.nextInt();
		B = scanner.nextInt();
		C = scanner.nextInt();
		Arr = new int[N + 1];
		for (int i = 1; i < N + 1; ++i) {
			Arr[i] = scanner.nextInt();
		}
		System.out.println(start(1, 0, 0));
		scanner.close();
	}

	private static int start(int start, int cost, int depth) {
		System.out.println("come to point " + start);
		if (start == N)
			return cost;
		if (depth > N)
			return Integer.MAX_VALUE;
		int plan1 = Integer.MAX_VALUE;
		int plan2 = Integer.MAX_VALUE;
		int plan3 = Integer.MAX_VALUE;

		plan1 = start(Arr[start], cost + A, depth + 1);

		if (Arr[start] > 1 && Arr[start] > N) {
			for (int i = 1; Arr[start] - i > 0; ++i) {
				Arr[start] -= i;
				plan2 = start(Arr[start], cost + B, depth + 1);
				Arr[start] += i;
			}
		}

		if (Arr[start] < N) {
			for (int i = 1; Arr[start] + i <= N; ++i) {
				Arr[start] += i;
				plan3 = start(Arr[start], cost + C, depth + 1);
				Arr[start] -= i;
			}
		}
		return Math.min(Math.min(plan1, plan2), plan3);
	}
}
