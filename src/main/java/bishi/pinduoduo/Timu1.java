package bishi.pinduoduo;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Timu1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] split = scanner.nextLine().split(";");
		int N = Integer.valueOf(split[1]);
		String[] dataStr = split[0].split(",");
		PriorityQueue<Integer> oddQueue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		PriorityQueue<Integer> ouQueue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		for (int i = 0; i < dataStr.length; ++ i) {
			int curData = Integer.parseInt(dataStr[i]);
			if (curData % 2 == 0)
				ouQueue.add(curData);
			else
				oddQueue.add(curData);
		}
		int count = 0;
		while (!ouQueue.isEmpty() && count < N) {
			System.out.print(count == N - 1 ? ouQueue.poll() + "\n" : ouQueue.poll() + "," );
			count ++;
		}
		while (!oddQueue.isEmpty() && count < N) {
			System.out.print(count == N - 1 ? oddQueue.poll() + "\n" : oddQueue.poll() + "," );
			count ++;
		}
		scanner.close();
	}
}
