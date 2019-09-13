package bishi.aiqiyi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main2 {

	static int N;
	static int[] An;
	static Set<Integer> visited = new HashSet<>();
	static TreeSet<Integer> unVisited = new TreeSet<>();
	static final int MOD = (int) Math.pow(10, 9) + 7;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = Integer.parseInt(scanner.nextLine());
		An = new int[N - 1];
		int countOne = 0;
		int countZero = 0;
		for (int i = 0; i < N - 1; ++i) {
			An[i] = scanner.nextInt();
			countOne += An[i];
			unVisited.add(i + 1);
		}
		countZero = N - countOne - 1;
		unVisited.add(N);
		int ans = 0;
		for (int i = 1; i <= N; ++i) {
			ans += dfs(i, 0, countOne, countZero);
			ans %= MOD;
		}
		System.out.println(ans);
		scanner.close();
	}

	private static int dfs(int toVis, int count, int countOne, int countZero) {
		if ((toVis <= countOne && An[count] == 1) || (toVis + countZero > N && An[count] == 0))
			return 0;
		if (count == N - 1) {
			return 1;
		}
		visited.add(toVis);
		unVisited.remove(toVis);
		int ans = 0;
		for (Object i1 : unVisited.toArray()) {
			int i = (Integer) i1;
			if (An[count] == 0 && !visited.contains(i) && i > toVis) {
				ans += dfs(i, count + 1, countOne, countZero - 1);
			} else if (!visited.contains(i) && An[count] == 1 && i < toVis) {
				ans += dfs(i, count + 1, countOne - 1, countZero);
			}
			ans %= MOD;
		}
		visited.remove(toVis);
		unVisited.add(toVis);
		return ans;
	}
}
/**
 * 100 0 0 0 1 1 0 0 1 0 1 0 1 0 1 0 0 1 0 0 1 1 0 0 0 1 1 1 1 0 0 1 0 0 0 0 0 1
 * 1 0 1 1 0 1 1 1 1 1 0 0 0 0 0 0 1 1 0 0 1 1 1 0 1 0 1 1 1 0 0 0 1 0 0 1 0 1 1
 * 1 1 0 1 1 0 1 0 1 0 1 1 0 0 1 0 1 1 1 0 0 1 0
 * 
 * 50 0 0 1 1 0 0 1 1 1 0 0 0 1 0 0 1 1 0 0 1 0 1 0 1 1 0 0 0 0 1 1 1 1 1 1 1 0
 * 1 0 1 0 1 0 0 1 1 0 0 1
 */
