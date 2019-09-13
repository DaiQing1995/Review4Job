package bishi.jd;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	private static class People implements Comparable<People> {
		int id;
		LinkedList<Integer> relations;

		public People(int id) {
			this.id = id;
			relations = new LinkedList<Integer>();
		}

		public void deleteMan(int id) {
			for (int i = 0;i < relations.size(); ++ i) {
				if (relations.get(i) == id) {
					relations.remove(i);
					return;
				}
			}
		}
		
		@Override
		public int compareTo(People o) {
			if (o.relations.size() > this.relations.size())
				return 1;
			else if (o.relations.size() < this.relations.size()) {
				return -1;
			}
			return this.id - o.id;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int relationCount = m;
		HashMap<Integer, People> peopleDic = new HashMap<>();
		LinkedList<People> men = new LinkedList<>();
		sc.nextLine();
		for (int i = 0; i < m; ++i) {
			int boyIndex = sc.nextInt();
			int girlIndex = sc.nextInt();
			sc.nextLine();
			if (!peopleDic.containsKey(boyIndex)) {
				People people = new People(boyIndex);
				people.relations.add(girlIndex);
				peopleDic.put(boyIndex, people);
			}else 
				peopleDic.get(boyIndex).relations.add(girlIndex);
			if (!peopleDic.containsKey(girlIndex)) {
				People people = new People(girlIndex);
				people.relations.add(boyIndex);
				peopleDic.put(girlIndex, people);
			}else { 
				peopleDic.get(girlIndex).relations.add(boyIndex);
			}
		}
		men.addAll(peopleDic.values());
		Collections.sort(men);
		Set<Integer> result = new TreeSet<>();
		while(relationCount > 0) {
			People poll = men.poll();
			result.add(poll.id);
			relationCount -= poll.relations.size();
			for (int i = 0; i < poll.relations.size(); ++ i) {
				People peopleTar = peopleDic.get(poll.relations.get(i));
				peopleTar.deleteMan(poll.id);
			}
			Collections.sort(men);
		}
		System.out.println(result.size());
		for (Integer res : result) {
			System.out.print(res + " ");
		}
		sc.close();
	}
}
/*
2 3
1 3
1 4
2 3
 */