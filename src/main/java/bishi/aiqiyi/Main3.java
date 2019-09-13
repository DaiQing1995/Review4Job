package bishi.aiqiyi;

import java.util.Scanner;

public class Main3 {

	static int N, M;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		int ball = (N + M) % 3;
		int cBalls = (N + M) / 3;
		int aBalls = cBalls;
		int bBalls = cBalls;
		if (ball <= 2) {
			aBalls ++;
			if (ball == 2)
				bBalls ++;
		}
		
		scanner.close();
	}

}
/**
 * 100 0 0 0 1 1 0 0 1 0 1 0 1 0 1 0 0 1 0 0 1 1 0 0 0 1 1 1 1 0 0 1 0 0 0 0 0 1
 * 1 0 1 1 0 1 1 1 1 1 0 0 0 0 0 0 1 1 0 0 1 1 1 0 1 0 1 1 1 0 0 0 1 0 0 1 0 1 1
 * 1 1 0 1 1 0 1 0 1 0 1 1 0 0 1 0 1 1 1 0 0 1 0
 * 
 * 50 0 0 1 1 0 0 1 1 1 0 0 0 1 0 0 1 1 0 0 1 0 1 0 1 1 0 0 0 0 1 1 1 1 1 1 1 0
 * 1 0 1 0 1 0 0 1 1 0 0 1
 */
