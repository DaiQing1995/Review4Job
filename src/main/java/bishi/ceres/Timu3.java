package bishi.ceres;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Timu3 {

	public static class Baker implements Comparable<Baker> {

		int maxBiscuit;
		int perMinBiscuit;

		int totalBiscuits;

		public Baker(int perMinBiscuit, int maxBiscuit, int t) {
			super();
			this.maxBiscuit = maxBiscuit;
			this.perMinBiscuit = perMinBiscuit;
			if (t * perMinBiscuit > maxBiscuit)
				totalBiscuits = maxBiscuit;
			else
				totalBiscuits = t * perMinBiscuit;
		}

		@Override
		public int compareTo(Baker o) {
			if (totalBiscuits > o.totalBiscuits)
				return -1;
			else if (totalBiscuits == o.totalBiscuits)
				return 0;
			else
				return 1;
		}

	}

	private static class SpeedComparator implements Comparator<Baker>{
		@Override
		public int compare(Baker o1, Baker o2) {
			if (o1.perMinBiscuit > o2.perMinBiscuit) {
				return -1;
			} else if (o1.perMinBiscuit < o2.perMinBiscuit) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	private static PriorityQueue<Baker> bakers = new PriorityQueue<>();
	private static ArrayList<Baker> bakersRemain = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt(); // 饼干数
		int n = sc.nextInt(); // 烤箱数
		int t = sc.nextInt(); // 时间限
		int k = sc.nextInt(); // 可用数目

		for (int i = 0; i < n; i++) {
			Baker baker = new Baker(sc.nextInt(), sc.nextInt(), t);
			bakers.add(baker);
		}
		// 1. 生产量判断
		boolean flag = false;
		int count = 0;
		ArrayList<Baker> finalBakers = new ArrayList<>();
		int totalBiscuits = 0;
		while (!bakers.isEmpty() && count < k) {
			count++;
			Baker poll = bakers.poll();
			finalBakers.add(poll);
			totalBiscuits += poll.totalBiscuits;
		}
		flag = totalBiscuits < l ? false : true;

		if (!flag) {
			System.out.println("No\n" + totalBiscuits);
			return;
		}

		// 2.生产力排序
		while (!bakers.isEmpty()) {
			bakersRemain.add(bakers.poll());
		}
		Collections.sort(bakersRemain, new SpeedComparator());
		Collections.sort(finalBakers, new SpeedComparator());
		// 确定finalBakers
		while (totalBiscuits - finalBakers.get(finalBakers.size() - 1).totalBiscuits
				+ bakersRemain.get(0).totalBiscuits >= l) {
			finalBakers.remove(finalBakers.size() - 1);
			finalBakers.add(bakersRemain.remove(0));
			Collections.sort(finalBakers, new SpeedComparator());
		}
		int time = 0;
		totalBiscuits = 0;
		while(totalBiscuits < l) {
			for (int i = 0;i < finalBakers.size(); ++ i) {
				Baker baker = finalBakers.get(i);
				if (baker.totalBiscuits < time * baker.perMinBiscuit) {
					totalBiscuits += baker.totalBiscuits - time * baker.perMinBiscuit; 					
				}else {
					totalBiscuits += baker.perMinBiscuit;
				}
			} 
			time ++;
		}
		System.out.println("Yes\n" + time);
	}

}
