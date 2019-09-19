package bishi.didi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * ��Ŀ������������������װ��ͻ�����������������������װ������Ҫ��ͬ(������޷��ټӻ�����ӵ�һЩ)
 * ���ƣ�һ�����������������ϵ�
 * ���룺
 * 5 3��5�������� 3����ϵ��
 * 1 2����ʾ1��2���ܷ�һ��
 * 2 3
 * 3 4
 * ���:
 * 4
 * 
 * ��ǰ˼·��dfs�������о��ߣ�ѡ������
 * ���ܸ��õ�˼·������Ƿ��л���
 * 
 * @author DaiQing
 */
public class Main {

	static int[] from;
	static int[] against;

	static Map<Integer, Set<Integer>> againstRelation = new HashMap<>();
	static HashSet<Integer> nonRelation = new HashSet<>();

	public static int dfs(Set<Integer> car1, Set<Integer> car2, int depth) {
		if (depth >= from.length) {
			if (Math.abs(car1.size() - car2.size()) <= nonRelation.size())
				return Math.max(car1.size(), car2.size()) * 2;
			else {
				return 2 * (Math.max(car1.size(), car2.size())
						- (Math.abs(car1.size() - car2.size()) - nonRelation.size()));
			}
		}
		if (!againstRelation.containsKey(from[depth])) {
			againstRelation.put(from[depth], new HashSet<>());
			againstRelation.get(from[depth]).add(against[depth]);
		} else {
			againstRelation.get(from[depth]).add(against[depth]);
		}
		if (!againstRelation.containsKey(against[depth])) {
			againstRelation.put(against[depth], new HashSet<>());
			againstRelation.get(against[depth]).add(from[depth]);
		} else {
			againstRelation.get(against[depth]).add(from[depth]);
		}
		int plan1 = 0;
		int plan2 = 0;
		if (car1.contains(from[depth])) {
			if (!car2.contains(against[depth])) {
				Set<Integer> set = againstRelation.get(against[depth]);
				if (!containsAgainist(car2, against[depth])) {
					car2.add(against[depth]);
					plan1 = dfs(car1, car2, depth + 1);
					car2.remove(against[depth]);
				}
			}
		} else {
			if (!containsAgainist(car1, from[depth])) {
				if (!containsAgainist(car1, from[depth])) {
					car1.add(from[depth]);
				}
			}
		}
		if (car2.contains(from[depth])) {

		} else {

		}
		return Math.max(plan1, plan2);
	}

	public static boolean containsAgainist(Set<Integer> car, int data) {
		Set<Integer> set = againstRelation.get(data);
		boolean flag = false;
		for (int x : set) {
			if (car.contains(x)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] split = scanner.nextLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		for (int i = 1; i <= N; ++i)
			nonRelation.add(i);
		from = new int[M];
		against = new int[M];
		for (int i = 0; i < M; ++i) {
			String[] fromTo = scanner.nextLine().split(" ");
			from[i] = Integer.parseInt(fromTo[0]);
			against[i] = Integer.parseInt(fromTo[1]);
			if (nonRelation.contains(from[i]))
				nonRelation.remove(from[i]);
			if (nonRelation.contains(against[i]))
				nonRelation.remove(against[i]);
			if (againstRelation.containsKey(from[i])) {
				againstRelation.get(from[i]).add(against[i]);
			} else {
				againstRelation.put(from[i], new HashSet<>());
				againstRelation.get(from[i]).add(against[i]);
			}
			if (againstRelation.containsKey(against[i])) {
				againstRelation.get(against[i]).add(from[i]);
			} else {
				againstRelation.put(against[i], new HashSet<>());
				againstRelation.get(against[i]).add(from[i]);
			}
		}
		System.out.println(dfs(new HashSet<>(), new HashSet<>(), 0));
		scanner.close();
	}
}