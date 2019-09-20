package bishi.tencent;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 90% pass
 * Timu 4
 * @author DaiQing
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		PriorityQueue<Integer> pQueue = new PriorityQueue<>();
		int substract = 0;
		for (int i = 0; i < n; ++ i) {
			pQueue.add(scanner.nextInt());
		}
		for (int i = 0;i < k; ++ i) {
			boolean flag = true;
			List<Integer> tmpList = new LinkedList<Integer>();
			while(pQueue.size() != 0) {
				int tmpData = pQueue.poll();
				while(tmpData - substract == 0) tmpData = pQueue.poll();
				if (tmpData - substract != 0) {
					flag = false;
					System.out.println(tmpData - substract);
					substract += tmpData - substract;
					break;
				}else {
					tmpList.add(tmpData);
				}
			}
			if (flag) {
				System.out.println("0");
			}
			pQueue.addAll(tmpList);
		}
		scanner.close();
	}
}
