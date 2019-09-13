package bishi.baidu;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		for (int i = 0; i < n; ++i) {
			long cur = sc.nextLong();
			if (cur == 1) {
				System.out.println(1);
				continue;
			}
			cur -= 1;
			long t = cur / 6;
			long l = cur % 6;
			if (l == 0) {
				System.out.println( 4 * t + 1);
			} else if (l == 1) {
				System.out.println( 3 * t + 1);
			} else if (l == 2) {
				System.out.println(t);
			} else if (l == 3) {
				System.out.println(3 * (2 * t + 1));
			} else if (l == 4) {
				System.out.println(t);
			} else if (l == 5) {
				System.out.println(3 * (t + 1));
			}
		}
	}

}
