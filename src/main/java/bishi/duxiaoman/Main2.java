package bishi.duxiaoman;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ÌâÄ¿1 pass 0.09
 * @author DaiQing
 *
 */
public class Main2 {

	private static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			return x + y;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pos obj1 = (Pos)obj;
			if (obj1.x == x && obj1.y == y)
				return true;
			else
				return false;
		}
	}
	
	static boolean[][] map = new boolean[1001][1001];
	private static int targetX;
	private static int targetY;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		targetX = scanner.nextInt() + 500;
		targetY = scanner.nextInt() + 500;
		int n = scanner.nextInt();
		for (int i = 0; i < n; ++i) {
			int tmpX = 500 + scanner.nextInt();
			int tmpY = 500 + scanner.nextInt();
			map[tmpX][tmpY] = true;
		}
		tolerateDisX = Math.abs(targetX - 500) + 5;
		tolerateDisY = Math.abs(targetY - 500) + 5;
		find(500, 500, 0);
		System.out.println(maxAns);
		scanner.close();
	}

	final static int[] moveX = new int[] { -1, 1, 0, 0 };
	final static int[] moveY = new int[] { 0, 0, -1, 1 };
	static int maxAns = Integer.MAX_VALUE;
	static Set<Pos> visited = new HashSet<>();
	static int tolerateDisX;
	static int tolerateDisY;

	private static void find(int posX, int posY, int depth) {
		if (posX == targetX && posY == targetY) {
			if (maxAns > depth)
				maxAns = depth;
			return;
		}
		if (depth > maxAns)
			return;
		Pos tmpPos = new Pos(posX, posY);
		visited.add(tmpPos);
		for (int i = 0; i < 4; ++i) {
			int newPosX = posX + moveX[i];
			int newPosY = posY + moveY[i];
			if (isAvail(newPosX, newPosY)) {
				find(newPosX, newPosY, depth + 1);
			}
		}
		visited.remove(tmpPos);
	}

	private static boolean isAvail(int posX, int posY) {
		Pos pos = new Pos(posX, posY);
		if (!visited.contains(pos) && !map[posX][posY] && posX < 1001 && posX < 1001 && posX >= 0 && posY >= 0 && Math.abs(posX - targetY) <= tolerateDisX
				&& Math.abs(posY - targetY) <= tolerateDisY)
			return true;
		return false;
	}
}
