package bishi.frequent;

import java.util.Random;

public class TestCaseGenerator {
	public static void main(String[] args) {
		Random random = new Random();
		System.out.println(1000 + " " + 2000);
		for (int i = 0; i < 2000; i ++) {
			int from = 1 + random.nextInt(1000);
			int to = 1 + random.nextInt(1000);
			System.out.print(from + " " + to + (i == 1999 ? "\n" : " "));
		}
	}
}
