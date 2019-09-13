package bishi.beikei2;

import java.util.Scanner;

public class Main {

	static int isAvail(String str, int index) {
		int rear = str.length() - 1;
		int tmp = index + 1;
		for (int i = index; i >= 0; --i) {
			if (str.charAt(rear) != str.charAt(i)) {
				return -1;
			}
			rear --;
		}
		return rear;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		sc.nextLine();
		String chars = sc.nextLine();
		int indexFst = chars.substring(0, chars.length() - 1).lastIndexOf(chars.charAt(chars.length() - 1));
		if (indexFst == chars.length() - 1) {
			for (int i = 0; i < k; ++i) {
				System.out.print(chars);
				return ;
			}
		}
		int indexNew;
		if ((indexNew = isAvail(chars, indexFst)) == -1) {
			for (int i = 0; i < k; ++i) {
				System.out.print(chars);
				return ;
			}
		}else {
			for (int i = 0;i < k; ++ i) {
				for (int j = 0; j <= indexNew; ++ j) {
					System.out.print(chars.charAt(j));
				}
			}
			for (int i = indexNew + 1; i < n; ++ i) {
				System.out.print(chars.charAt(i));
			}
		}
	}
}