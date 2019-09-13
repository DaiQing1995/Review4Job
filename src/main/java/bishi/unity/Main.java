package bishi.unity;

import java.util.Scanner;

public class Main {

	static int F;

	public static class State{
		int index;
		int money; 
		boolean hold;
		
		public State(int index, int money, boolean hold) {
			super();
			this.index = index;
			this.money = money;
			this.hold = hold;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] split = sc.nextLine().split(" ");
		F = Integer.parseInt(split[0]);
		int N = Integer.parseInt(split[1]);
		int[] val = new int[N];
		for (int i = 0; i < N; ++i) {
			val[i] = sc.nextInt();
		}
		System.out.println(dfs(val, 0, 0, false));
		sc.close();
	}

	private static int dfs(int[] val, int index, int money, boolean hold) {
		if (index == val.length - 1) {
			if (hold) {
				return money + val[index] - F;
			}else {
				return money;
			}
		}
		if (hold) {
			if (money + val[index] - F >= 0) {
				return Math.max(dfs(val, index + 1, money + val[index] - F, false), dfs(val, index + 1, money, true));	
			}else {
				return dfs(val, index + 1, money, true);
			}
		}else {
			return Math.max(dfs(val, index + 1, money - val[index], true), dfs(val, index + 1, money, false));	
		}
	}

}
