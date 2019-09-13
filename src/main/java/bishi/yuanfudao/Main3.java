package bishi.yuanfudao;

import java.util.Scanner;

public class Main3 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long N = scanner.nextInt();
		long K = scanner.nextInt();
		long ans = 1;
		for (long i = 0;i < K - 2; ++ i) {
			ans *= N - 1;
			ans %= 10000_00007;
		}
		ans *= N - 2;
		ans %= 10000_00007;
		System.out.println(ans);
		scanner.close();
	}
	
}
