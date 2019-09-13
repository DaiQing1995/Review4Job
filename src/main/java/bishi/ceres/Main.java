package bishi.ceres;

import java.util.*;

public class Main {

	public static class Path {
		static int bestPathTime = Integer.MAX_VALUE;
		static Path bestPath;
		int[] path;
		int takeBikeSum;
		int bringBikeSum;
		int time;

		Path(ArrayList<Integer> path1, int[][] graph, int[] station, int cMax) {
			this.path = new int[path1.size()];
			this.path[0] = 0;
			this.takeBikeSum = 0;
			this.bringBikeSum = 0;
			this.time = 0;
			for (int i = 1; i < path1.size(); ++i) {
				this.path[i] = path1.get(i);
				int stationBikes = station[this.path[i] - 1];
				if (stationBikes < cMax / 2) {
					int need = cMax / 2 - stationBikes;
					if (need <= this.bringBikeSum)
						this.bringBikeSum -= need;
					else {
						this.takeBikeSum += need - this.bringBikeSum;
						this.bringBikeSum = 0;
					}
				}else if (stationBikes > cMax / 2) {
					this.bringBikeSum += stationBikes - cMax / 2;
				}
				this.time += graph[path1.get(i - 1)][path1.get(i)];
			}
			if (this.time < bestPathTime) {
				Path.bestPath = this;
				bestPathTime = this.time;
			} else if (this.time == bestPathTime) {
				if (bestPath.takeBikeSum > this.takeBikeSum && bestPath.bringBikeSum > this.bringBikeSum) {
					Path.bestPath = this;
					bestPathTime = this.time;
				}
			}
		}

		public String toString() {
			StringBuilder pathStr = new StringBuilder();
			for (int i = 0; i < this.path.length; ++i) {
				pathStr.append(i == this.path.length - 1 ? path[i] : path[i] + "->");
			}
			return this.takeBikeSum + " " + pathStr.toString() + " " + this.bringBikeSum;
		}
	}

	static int cMax;
	static int N;
	static int Sp;
	static int M;

	static int[] station;
	static int[][] graph;

	static ArrayList<Integer> currentPath = new ArrayList<>();
	static int[][] visited;

	public static void dfs(int start, int end) {
		int minIndex = 0;
		int min;
		int currentNode = start;
		HashSet<Integer> nodesCaled = new HashSet<>();
		nodesCaled.add(start);
		while (true) {
			min = Integer.MAX_VALUE / 2;
			boolean findNode = false;
			// 1.选取最小节点
			for (int i = 0; i < N + 1; ++i) {
				if (nodesCaled.contains(i))
					continue;
				int val = graph[currentNode][i];
				if (visited[currentNode][i] != -1 && val != -1 && min > val) {
					min = val;
					minIndex = i;
					findNode = true;
				}
			}
			if (findNode) {
				visited[currentNode][minIndex] = -1;
				currentNode = minIndex;
				currentPath.add(minIndex);
				nodesCaled.add(minIndex);
				// 2.判断到达
				if (minIndex == Sp) {
//					if (currentPath.get(0) == 0 && currentPath.get(1) == 5&&currentPath.get(2) == 1&&currentPath.get(3) == 4)
//						System.out.println("check point");
					new Path(currentPath, graph, station, cMax);
				} else {// 未到达，继续寻找
					continue;
				}
			}
			// 3.开始回溯（未找到路径，回溯；已经到达，开始找其它路径，回溯）
			nodesCaled.remove(currentPath.get(currentPath.size() - 1));
			for (int j = 0; j < N + 1; ++j) {
				visited[currentPath.get(currentPath.size() - 1)][j] = 
						currentPath.get(currentPath.size() - 1) == j ? -1 : 1;
//				visited[j][currentPath.get(currentPath.size() - 1)] =visited[currentPath.get(currentPath.size() - 1)][j]; 
						
			}
			currentPath.remove(currentPath.size() - 1);
			if (!currentPath.isEmpty())
				currentNode = currentPath.get(currentPath.size() - 1);
			else
				break;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cMax = sc.nextInt();
		N = sc.nextInt();
		Sp = sc.nextInt();
		M = sc.nextInt();
		station = new int[N];
		for (int i = 0; i < N; ++i) {
			station[i] = sc.nextInt();
		}

		graph = new int[1 + N][1 + N];
		visited = new int[1 + N][1 + N];
		for (int i = 0; i < 1 + N; ++i) {
			for (int j = 0; j < 1 + N; ++j) {
				graph[i][j] = i == j ? 0 : -1;
				visited[i][j] = i == j ? -1 : 1;
			}
		}
		int start, end, time;
		for (int i = 0; i < M; ++i) {
			start = sc.nextInt();
			end = sc.nextInt();
			time = sc.nextInt();
			graph[start][end] = time;
			graph[end][start] = time;
		}
		sc.close();
		currentPath.add(0);
		dfs(0, Sp);
		System.out.println(Path.bestPath);
	}
}