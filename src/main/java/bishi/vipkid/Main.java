package bishi.vipkid;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int ans = 0;
		while(n > 0) {
			if ((n & 1) == 1) ans ++;
			n >>>= 1;
		}
		System.out.println(ans);
		scanner.close();
	}
}
