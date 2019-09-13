package bishi.frequent;

public class Solution {

	int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	int rows;
	int cols;
	int threshold;
	boolean[][] flag;
	boolean[][] visited;
	public int move(int x, int y) {
		int max = 0;
		visited[x][y] = true;
		for (int i = 0; i < 4; ++i) {
			int newX = x + dir[i][0];
			int newY = y + dir[i][1];
			if (!inBound(newX, newY) || visited[newX][newY]) {
				continue;
			}
			flag[newX][newY] = getSum(newX) + getSum(newY) <= threshold ? true : false ;
			if (flag[newX][newY]) {
				max = Math.max(move(newX, newY), max);
			}
		}
		visited[x][y] = false;
		return 1 + max;
	}

	public int getSum(int x) {
		int tmp = x;
		int ret = 0;
		while (tmp > 0) {
			ret += tmp % 10;
			tmp /= 10;
		}
		return ret;
	}

	public boolean inBound(int x, int y) {
		if (x < 0 || x >= rows || y < 0 || y >= cols)
			return false;
		return true;
	}

	public int movingCount(int threshold, int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.threshold = threshold;
		flag = new boolean[rows][cols];
		visited = new boolean[rows][cols];
		return move(0, 0);
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.movingCount(5, 5, 5));
	}
}