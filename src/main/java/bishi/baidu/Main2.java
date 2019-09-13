package bishi.baidu;

import java.util.Scanner;

public class Main2 {
	
	public static int getHighest(int[] val) {
		for (int i = val.length - 1; i >= 0; i--) {
			if (val[i] > 0)
				return i;
		}
		return 0;
	}

	public static int maxCommonDivisor(int m, int n) {
		if (m < n) {
			int temp = m;
			m = n;
			n = temp;
		}
		if (m % n == 0) {
			return n;
		} else {
			return maxCommonDivisor(n, m % n);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; ++i) {
			int high = sc.nextInt();
			int[] fst = new int[high];
			int[] snd = new int[high];
			for (int j = 0; j < high; ++j) {
				fst[j] = sc.nextInt();
			}
			boolean same = true;
			for (int j = 0; j < high; ++j) {
				snd[j] = sc.nextInt();
				if (snd[j] != fst[j])
					same = false;
			}
			int highestFst = getHighest(fst);
			int highestSnd = getHighest(snd);
			if (highestFst == highestSnd) {
				int maxCommonDivisor = maxCommonDivisor(fst[highestFst], snd[highestSnd]);
				System.out.println(fst[highestFst] / maxCommonDivisor + "/" + snd[highestSnd] / maxCommonDivisor);
			} else if (highestFst > highestSnd) {
				System.out.println("1/0");
			} else {
				System.out.println("0/1");
			}
		}
	}
}
