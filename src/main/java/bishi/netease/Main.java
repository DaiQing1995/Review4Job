package bishi.netease;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	static int N;
	static int[] A;
	
	public static String printDouble(double ans) {
		if (ans == 0.0) {
			return "0.000000";
		}
		for (int i = 0;i < 8; ++ i)
			ans *= 10;
		if (ans % 10 >= 5) {
			ans += 1;
		}
		int ansNew = (int)ans;
		ArrayList<Integer> xiaoshu = new ArrayList<>();
		for(int i = 0;i < 6; ++ i) {
			xiaoshu.add(ansNew % 10);
			ansNew /= 10;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = xiaoshu.size() - 1;i >= 0; i--) {
			stringBuilder.append(xiaoshu.get(i));
		}
		return String.valueOf(ansNew) + "." + stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		A = new int[N];
		int[] score = new int[151];
		Set<Integer> scoreIndex = new TreeSet<>();
		for (int i = 0; i < N; ++ i) {
			A[i] = scanner.nextInt();
			scoreIndex.add(A[i]);
			score[A[i]] ++;
		}
		
		int count = 0;
		double[] ans = new double[151]; 
		for (Integer index : scoreIndex) {
			count += score[index];
			ans[index] = 100 * (double)(count - 1) / (double)N;
		}
		
		int Q = scanner.nextInt();
		for (int i = 0; i < Q; ++ i) {
			int tmp = scanner.nextInt();
//			String haha = String.format("%.6f", 66.66666666666666);
			System.out.println(printDouble(ans[A[tmp - 1]]));
		}
		
		scanner.close();
	}
}
