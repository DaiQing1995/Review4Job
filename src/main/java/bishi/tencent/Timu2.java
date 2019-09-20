package bishi.tencent;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 100% pass
 * @author DaiQing
 *
 */
public class Timu2 {
	
	private static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		int M = 0;
		int maxVal = 0;
		PriorityQueue<Pair> pQueueSmall = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.y - o2.y;
			}
		});
		PriorityQueue<Pair> pQueueBig = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o2.y - o1.y;
			}
		});
		for (int i = 0; i < n; ++i) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			M += x;
			Pair pair = new Pair(x, y);
			pQueueSmall.add(pair);
			pQueueBig.add(pair);
		}
		int count = 0;
		while(count < M / 2) {
			Pair pair1 = pQueueBig.peek();
			Pair pair2 = pQueueSmall.peek();
			maxVal = Math.max(maxVal, pair1.y + pair2.y);
			if (pair1.x == pair2.x) {
				pQueueBig.poll();
				pQueueSmall.poll();
				count += pair1.x;
			}else if (pair1.x > pair2.x) {
				pQueueSmall.poll();
				pair1.x = pair1.x - pair2.x;
				count += pair2.x;
			}else if (pair1.x < pair2.x) {
				pQueueBig.poll();
				pair2.x = pair2.x - pair1.x;
				count += pair1.x;
			}
		}
		System.out.println(maxVal);
		scanner.close();
	}
}
