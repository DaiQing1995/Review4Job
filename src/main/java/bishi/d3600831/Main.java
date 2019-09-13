package bishi.d3600831;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	private static int[] move;
	private static int n;
	private static int m;
	private static Set<Integer> result;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		if (m == 0) {
			System.out.println(n);
			return;
		}
		move = new int[m];
		result = new HashSet<>();
		for (int i = 0; i < m; ++i) {
			move[i] = scanner.nextInt();
		}
		for (int i = 1; i <= n; ++ i) {
			addAvail(i, 0);
		}
		System.out.println(result.size());
		scanner.close();
	}

	private static void addAvail(int index, int moveIndex) {
		if (index + move[moveIndex] <= n) {
			if (moveIndex == move.length - 1) {
				result.add(index + move[moveIndex]);
			}else
				addAvail(index + move[moveIndex], moveIndex + 1);
		}
		if(index - move[moveIndex] >= 1){
			if (moveIndex == move.length - 1) {
				result.add(index - move[moveIndex]);
			}else
				addAvail(index - move[moveIndex], moveIndex + 1);
		}
	}
}
