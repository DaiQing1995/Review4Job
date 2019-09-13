package bishi.huawei2;

import java.util.*;

public class Main3 {

	public static class Connection implements Comparable<Connection> {
		List<Integer> from;
		List<Integer> to;
		List<Integer> weight;
		List<String> names;
		
		//标记要删除的边下标
		List<Integer> deleted;
		//辅助Pqueue记录当前访问到的边
		int count = 0;

		public Connection() {
			from = new ArrayList<Integer>();
			to = new ArrayList<Integer>();
			weight = new ArrayList<Integer>();
			names = new ArrayList<String>();
			deleted = new ArrayList<Integer>();
		}

		public void addRoad(int from, int to, int weight, String name) {
			this.from.add(from);
			this.to.add(to);
			this.weight.add(weight);
			this.names.add(name);
		}

		public int getMaxWeight() {
			int maxWeight = this.weight.get(0);
			for (int i = 0; i < this.weight.size(); ++i) {
				if (weight.get(i) > maxWeight) {
					maxWeight = weight.get(i);
				}
			}
			return maxWeight;
		}

		@Override
		public int compareTo(Connection o) {
			int maxWeight = this.getMaxWeight();
			int maxWeight2 = o.getMaxWeight();
			if (maxWeight > maxWeight2)
				return -1;
			else if (maxWeight == maxWeight2) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	static boolean CheckCircle(List<Connection> connections, int M) {
		int[][] graph = new int[M][M];

		return false;
	}

	static Connection[][] connections;

	static PriorityQueue<Connection> pQueue = new PriorityQueue<>();
	static HashSet<Connection> cSet = new HashSet<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		connections = new Connection[M][M];
		sc.nextLine();
		for (int i = 0; i < N; ++i) {
			String[] split = sc.nextLine().split(",");
			int from = Integer.parseInt(split[0].substring(1, split[0].length()));
			int to = Integer.parseInt(split[1].substring(1, split[1].length()));
			if (connections[from - 1][to - 1] == null)
				connections[from - 1][to - 1] = new Connection();
			connections[from - 1][to - 1].addRoad(from, to, Integer.parseInt(split[3]), split[2]);
			if (!cSet.contains(connections[from - 1][to - 1]))
				cSet.add(connections[from - 1][to - 1]);
		}
		for (Connection connection : cSet)
			pQueue.add(connection);

		List<Connection> connections = new ArrayList<Connection>();
		Set<Integer> nodes = new HashSet<>();
		List<Connection> ans = new ArrayList<Connection>();
		while (pQueue.size() != 0) {
			Connection poll = pQueue.poll();
			if (CheckCircle(connections, M)) {
				nodes.add(poll.from.get(poll.count));
				nodes.add(poll.to.get(poll.count));
				poll.count++;
				if (poll.from.size() > poll.count)
					pQueue.add(poll);
			}else {
				ans.add(poll);
			}
		}
		if (ans.size() == 0)
			System.out.println("#");
		for (int i = 0;i < ans.size(); ++ i) {
			System.out.println(ans.get(i).names.get(0));
		}
		sc.close();
	}

}
