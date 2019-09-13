package bishi.huawei2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Maincw {

	static Map<String, Boolean> names = new HashMap<>();

	static Map<String, ArrayList<ArrayList<String>>> name2Group = new HashMap<>();

	static ArrayList<ArrayList<String>> groups = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String target = sc.nextLine().trim();
		int n = Integer.parseInt(sc.nextLine().trim());
		for (int i = 0; i < n; ++i) {
			String[] split = sc.nextLine().split(",");
			ArrayList<String> group = new ArrayList<>();
			for (int j = 0; j < split.length; ++j) {
				group.add(split[j]);
				names.put(split[j], false);
				if (!name2Group.containsKey(split[j])) {
					name2Group.put(split[j], new ArrayList<>());
				}
				name2Group.get(split[j]).add(group);
			}
			groups.add(group);
		}
		LinkedList<String> list = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		list.add(target);
		while (!list.isEmpty()) {
			String poll = list.poll();
			visited.add(poll);
			ArrayList<ArrayList<String>> groups = name2Group.get(poll);
			for (ArrayList<String> group : groups) {
				for (String s : group) {
					if (!visited.contains(s))
						list.add(s);
				}
			}
		}
		System.out.println(visited.size());
		sc.close();
	}
}
/**
Jack
3
Jack,Tom,Anny,Lucy
Tom,Danny
Jack,Lily
*/