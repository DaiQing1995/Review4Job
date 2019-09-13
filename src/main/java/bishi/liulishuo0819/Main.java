package bishi.liulishuo0819;

import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(getVal(90));
    }

	private static int getVal(int n) {
		int fPre1 = 1;
		int fPre2 = 2;
		if (n == 1 || n == 0)
			return 1;
		if (n == 2)
			return 2;
		int val;
		int tmp;
		for (int i = 2; i < n; ++ i) {
			val = fPre1 + fPre2;
			tmp = fPre2;
			fPre2 = val;
			fPre1 = tmp;
		}
		return fPre2;
	}
    
}