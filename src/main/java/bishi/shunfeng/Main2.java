package bishi.shunfeng;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main2 {

	private static class Group {
		Set<Integer> language;
		Set<Integer> people;
		List<Group> conn;

		public Group() {
			language = new HashSet<>();
			people = new HashSet<>();
			conn = new LinkedList<>();
		}
	}

	static int n;
	static int m;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		int k = sc.nextInt();
		if (k == 0) {
			System.out.println(n);
			return;
		}
		sc.nextLine();
		HashSet<Integer> knowNothingPeople = new HashSet<>();
		for (int i = 1; i <= n; ++i) {
			knowNothingPeople.add(i);
		}
		HashMap<Integer, Group> lang2Gp = new HashMap<>();
		HashMap<Integer, List<Group>> man2Gps = new HashMap<>();
		LinkedList<Group> allGps = new LinkedList<>();
		for (int i = 0; i < k; ++i) {
			int curMan = sc.nextInt();
			int curLan = sc.nextInt();
			Group group;
			if (lang2Gp.containsKey(curLan)) {
				group = lang2Gp.get(curLan);
				group.people.add(curMan);
			} else {
				group = new Group();
				group.people.add(curMan);
				group.language.add(curLan);
				lang2Gp.put(curLan, group);
				allGps.add(group);
			}
			if (man2Gps.containsKey(curMan)) {
				List<Group> list = man2Gps.get(curMan);
				for (int j = 0; j < list.size(); ++j) {
					list.get(j).conn.add(group);
					group.conn.add(list.get(j));
				}
			} else {
				LinkedList<Group> gps = new LinkedList<>();
				gps.add(group);
				man2Gps.put(curMan, gps);
			}
			if (knowNothingPeople.contains(curMan))
				knowNothingPeople.remove(curMan);
		}
		int ans = knowNothingPeople.size();
		Set<Group> visited = new HashSet<>();
		for (int i = 0; i < allGps.size(); ++i) {
			Group group = allGps.get(i);
			if (!visited.contains(group)) {
				ans++;
			}
			dfsMark(group, visited);
		}
		System.out.println(ans - 1);
		sc.close();
	}

	private static void dfsMark(Group subGp, Set<Group> visited) {
		visited.add(subGp);
		for (int i = 0; i < subGp.conn.size(); ++i) {
			if (!visited.contains(subGp.conn.get(i)))
				dfsMark(subGp.conn.get(i), visited);
		}
	}

}
