package bishi.tencent;

import java.util.Scanner;

/**
 * 70% pass
 * why???
 * @author DaiQing
 *
 */
public class Timu1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int cases = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < cases; ++ i) {
			int n = Integer.parseInt(scanner.nextLine());
			char[] charArray = scanner.nextLine().toCharArray();
			if (n < 11 || (n == 11 && charArray[0] != '8')) {
				System.out.println("NO");
				continue;
			}
			int count = 0;
			while(charArray[count] != '8') count ++;
			if (n - 11 - count >= 0) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
		scanner.close();
	}
}
