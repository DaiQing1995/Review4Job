package NumberGenerator;

import java.util.ArrayList;

public class NumberGenerator {

	static ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();

	public static void dfs(int curNum, ArrayList<Integer> arr) {
		if (N - curNum <= 1) {
			arr.add(1);
			arrayList.add(arr);
			return;
		}
	}

	static int N = 4;

	public static void main(String[] args) {
		for (int i = 0; i < N; ++i) {
			dfs(N - i, new ArrayList<>());
		}
	}

}
