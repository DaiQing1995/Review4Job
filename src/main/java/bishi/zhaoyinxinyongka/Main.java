package bishi.zhaoyinxinyongka;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 题目2：找到每个节点最大边权和
 * 思路：从底向上更新节点
 * 通过0%
 * @author DaiQing
 *
 */
public class Main {

	private static class Node {
		Node parent;
		long toParentWeight;
		long maxVal = 0;
	}

	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Map<Integer, Node> id2Node = new HashMap<>();
		Set<Node> leaves = new HashSet<>();
		for (int i = 0; i < N; ++i) {
			Node node = new Node();
			id2Node.put(i + 1, node);
			leaves.add(node);
		}
		for (int i = 0; i < N - 1; ++i) {
			Node u = id2Node.get(sc.nextInt());
			Node v = id2Node.get(sc.nextInt());
			v.toParentWeight = sc.nextInt();
			v.parent = u;
			if (leaves.contains(u))
				leaves.remove(u);
		}
		for (Node node : leaves) {
			long tmp = 0;
			while(node.parent != null) {
				tmp += node.toParentWeight;
				if (node.parent.maxVal < tmp)
					node.parent.maxVal = tmp;
				if (tmp < 0)
					tmp = 0;
				node = node.parent;
			}
		}
		for (int i = 1; i <= N; ++ i)
			System.out.print(id2Node.get(i).maxVal + (i == N ? "\n" : " "));
		sc.close();
	}
}
