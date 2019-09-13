package bishi.netease;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		Random random = new Random();
		for (int i = 0;i < 10000; ++ i) {
			System.out.print(140 + random.nextInt(10) + " ");
		}
		System.out.println();
	}
}
