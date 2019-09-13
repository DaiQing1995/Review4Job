package bishi.ceres;

import java.util.Scanner;

public class Timu4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long result = 0l;

		long negativeMax = 0;
		long tmpNegative = 0;
		
		for (int i = 0; i < n; i++) {
			long val = sc.nextLong();
			result += val;
			if (tmpNegative + val <= 0) {
				tmpNegative += val;
			} else{
				tmpNegative = 0;
			}
			if (tmpNegative < negativeMax)
				negativeMax = tmpNegative;
		}

		System.out.println(result - negativeMax);
	}
}

