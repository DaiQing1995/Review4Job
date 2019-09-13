package bishi.dji;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line;
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			String[] split = line.trim().split(" ");
			int N = Integer.parseInt(split[0]);
			int P = Integer.parseInt(split[1]);
			int C = Integer.parseInt(split[2]);
			int[][] graph = new int[N][N];
			for (int i = 1;i <= P; ++ i) {
				line = sc.nextLine();
				split = line.trim().split(" "); 
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);
				int t = Integer.parseInt(split[2]);
				graph[x][y] = t;
			}
			int ans = 0;
			for (int i = 0; i < C; ++ i) {
				ans += (dijkstra(0, Integer.parseInt(sc.nextLine()), graph));
			}
			System.out.println(ans);
		}
		sc.close();
	}

	private static int dijkstra(int from, int to, int[][] graph) {
		ArrayList<Integer> Visited = new ArrayList<>();
		ArrayList<Integer> UNVisited = new ArrayList<>();
		int[] distance = new int[graph.length];
		for (int i = 0; i < distance.length; ++ i) 
			distance[i] = graph[0][i] == 0 ? Integer.MAX_VALUE - 200 : graph[0][i];
		Visited.add(0);
		for (int i = 1;i < graph.length; ++ i) {
			UNVisited.add(i);
		}
		int curNode = 0;
		while(!UNVisited.isEmpty()) {
			int min = 1000;
			int minIndex = 0;
			int newNode = 0;
			for (int i = 0; i < UNVisited.size(); ++ i) {
				newNode = UNVisited.get(i);
				if (graph[curNode][newNode] != 0 && graph[curNode][newNode] < min) {
					min = graph[curNode][newNode];
					minIndex = i;
				}
			}
			newNode = UNVisited.get(minIndex);
			int a = 0;
			for (int i = 0;i < graph.length; ++ i) {
				if (newNode != i && graph[newNode][i] + distance[newNode] < distance[i]) {
					distance[i] = graph[newNode][i] + distance[newNode];
				}
			}
			Visited.add(UNVisited.remove(minIndex));
			curNode = newNode;
		}
		return distance[to];
	}
}
