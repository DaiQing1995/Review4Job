package bishi.kuaishou;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
啊啊啊啊，题意理解有误。。我直接DFS走树结构了。。
应该DFS生成所有的路径，之后判断路径是否可用就行了。没时间了。。。希望面试官看到。。。
4 4
1 2 1
2 3 1
3 4 1
*/
public class Main {

	private static int N;
	private static int K;

	private static final int BLACK = -1;
	private static final int RED = 1;

	private static class Node {
		int id;
		List<Node> connections;
		List<Integer> color;

		public Node(int id) {
			this.id = id;
			connections = new LinkedList<>();
			color = new LinkedList<>();
		}
	}
	
	static Map<Integer, Node> nodes = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < N - 1; ++i) {
			String[] split = sc.nextLine().split(" ");
			Node node1, node2;
			if (!nodes.containsKey(Integer.parseInt(split[0]))) {
				node1 = new Node(Integer.parseInt(split[0]));
			}else {
				node1 = nodes.get(Integer.parseInt(split[0])); 
			}
			if (!nodes.containsKey(Integer.parseInt(split[1]))) {
				node2 = new Node(Integer.parseInt(split[1]));
			}else {
				node2 = nodes.get(Integer.parseInt(split[1])); 
			}
			node1.connections.add(node2);
			node1.color.add(Integer.parseInt(split[2]) == 1 ? BLACK : RED);
			node2.connections.add(node1);
			node2.color.add(Integer.parseInt(split[2]) == 1 ? BLACK : RED);
			nodes.put(node1.id, node1);
			nodes.put(node2.id, node2);
		}
		int ans = 0;
		Set<Integer> nodesSet = nodes.keySet();
		for (Integer nodeIndex : nodesSet) {
			Node node = nodes.get(nodeIndex);
			ans += dfs(node, 1, false);
		}
		System.out.println(ans);
		sc.close();
	}

	private static int dfs(Node node, int deep, boolean hasBlack) {
		if (deep == K) {
			if (hasBlack)
				return 1;
			else
				return 0;
		}
		int ans = 0;
		for (int i = 0; i < node.connections.size(); ++ i) {
			if (node.color.get(i) == BLACK) {
				ans += dfs(node.connections.get(i), deep + 1, true);
			}else {
				ans += dfs(node.connections.get(i), deep + 1, hasBlack);
			}
		}
		return ans;
	}
}