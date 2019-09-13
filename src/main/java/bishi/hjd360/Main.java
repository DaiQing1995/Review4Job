package bishi.hjd360;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	private static int n;
	private static int m;
	private static ArrayList<Integer> fst;
	private static ArrayList<Integer> snd;
	private static boolean[] flag2s;

	public static ArrayList<Integer> solution() {
		ArrayList<Integer> ret = new ArrayList<>();
		Collections.sort(fst);
		Collections.sort(snd);
		for (int i = fst.size() - 1; i >= 0; --i) {
			int pre = -1;
			int index = 0;
			for (int j = snd.size() - 1; j >= 0; --j) {
				int tmp = (fst.get(i) + snd.get(j)) % m;
				if (flag2s[j] == false && tmp >= pre) {
					pre = tmp;
					index = j;
				} else if (tmp < pre) {
					for (int k = 0; k < snd.size(); ++ k) {
						tmp = (fst.get(i) + snd.get(k)) % m;
						if (flag2s[k] == false && tmp >= pre) {
							pre = tmp;
							index = k;
						}else if (tmp < pre) {
							break;
						}
					}
					break;
				}
			}
			flag2s[index] = true;
			ret.add(pre);
		}
		Collections.sort(ret);
		return ret;
	}

	// public static int findSumEqual2(int sum){
	// int ans = 0;
	// for (int i = 0;i < fst.size(); ++ i) {
	// if (flag1s[i] == true)
	// continue;
	// for (int j = 0; j < snd.size(); ++ j) {
	// if (flag2s[j] == false && (fst.get(i) + snd.get(j)) % m == sum) {
	// ans ++;
	// flag2s[j] = true;
	// flag1s[i] = true;
	// break;
	// }
	// }
	// }
	// return ans;
	// }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		fst = new ArrayList<>();
		snd = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			fst.add(sc.nextInt());
		}
		for (int i = 0; i < n; ++i) {
			snd.add(sc.nextInt());
		}
		// ArrayList<Integer> result = new ArrayList<>();
		// flag1s = new boolean[n];
		flag2s = new boolean[n];

		// for (int i = m - 1; i >= 0; --i) {
		// int sum = findSumEqual2(i);
		// for (int j = 0;j < sum; ++ j)
		// result.add(i);
		// }
		ArrayList<Integer> result = solution();
		for (int i = result.size() - 1; i >= 0; --i)
			System.out.print(result.get(i) + " ");
	}
}
/**
 * 5 5 4 4 1 1 1 4 3 0 1 2
 */