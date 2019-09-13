package bishi.pinduoduo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Timu2 {

	private static ArrayList<String> result = new ArrayList<>();
	private static int S;
	private static int N;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		S = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < S; ++i) {
			String mei = scanner.nextLine();
			N = mei.length();
			String bai = scanner.nextLine();
			StringBuilder strategy = new StringBuilder();
			StringBuilder newMei = new StringBuilder();
			dfs(new StringBuilder(mei), newMei, bai, 0, strategy);
			Collections.sort(result);
			System.out.println("{");
			for (int k = 0; k < result.size(); ++k) {
				String string = result.get(k);
				for (int j = 0; j < string.length(); ++j) {
					System.out.print(j == string.length() - 1 ? string.charAt(j) + "\n" : string.charAt(j) + " ");
				}
			}
			System.out.println("}");
			result.clear();
		}
		scanner.close();
	}

	private static void dfs(StringBuilder mei, StringBuilder newMei, String bai, int times, StringBuilder strategy) {
		if (times == N) {
			if (newMei.toString().equals(bai)) {
				result.add(strategy.toString());
			}
			return;
		}
		if (mei.length() > 0) {
			strategy.append('d');
			char tmpCh = mei.charAt(0);
			mei.deleteCharAt(0);
			dfs(mei, newMei, bai, times + 1, strategy);
			mei.insert(0, tmpCh);
			strategy.deleteCharAt(strategy.length() - 1);
		}
		if (mei.length() > 0) {
			char tmpCh = mei.charAt(0);

			strategy.append('l');
			newMei.insert(0, tmpCh);
			mei.deleteCharAt(0);
			dfs(mei, newMei, bai, times + 1, strategy);

			mei.insert(0, tmpCh);
			newMei.deleteCharAt(0);
			strategy.deleteCharAt(strategy.length() - 1);
		}
		if (mei.length() > 0) {
			char tmpCh = mei.charAt(0);
			strategy.append('r');
			newMei.append(tmpCh);
			mei.deleteCharAt(0);
			dfs(mei, newMei, bai, times + 1, strategy);
			mei.insert(0, tmpCh);
			newMei.deleteCharAt(newMei.length() - 1);
			strategy.deleteCharAt(strategy.length() - 1);
		}
	}
}
