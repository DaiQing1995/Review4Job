package bishi;

import java.util.*;

public class Solution4 {

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object oth0) {
			Pos oth = (Pos) oth0;
			if (oth.x == this.x && oth.y == this.y)
				return true;
			return false;
		}

		public int hashCode() {
			return Integer.hashCode(x) + Integer.hashCode(y);
		}
	}

	int[][] move = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	Set<Pos> visited = new HashSet<>();

	public boolean getPath(char[][] matrixNew, int rows, int cols, char[] str, Pos indexMat, int indexStr, Pos tabu) {
		if (indexStr == str.length - 1) {
			if (str[indexStr] == matrixNew[indexMat.x][indexMat.y]) {
				return true;
			} else {
				return false;
			}
		}
		visited.add(indexMat);
		for (int i = 0; i < 4; ++i) {
			int newX = indexMat.x + move[i][0];
			int newY = indexMat.y + move[i][1];
			if (inBound(rows, cols, newX, newY) && matrixNew[newX][newY] == str[indexStr + 1]) {
				if (getPath(matrixNew, rows, cols, str, new Pos(newX, newY), indexStr + 1, indexMat))
					return true;
			}
		}
		visited.remove(indexMat);
		return false;
	}

	public boolean inBound(int rows, int cols, int indexI, int indexJ) {
		return (indexI < rows && indexI >= 0) && (indexJ < cols && indexJ >= 0)
				&& (!visited.contains(new Pos(indexI, indexJ)));
	}

	public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
		if (str.length == 0 || matrix.length == 0)
			return false;
		char[][] matrixNew = new char[rows][cols];
		int nIndex = 0;
		ArrayList<Pos> starts = new ArrayList<>();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				matrixNew[i][j] = matrix[nIndex++];
				if (matrixNew[i][j] == str[0])
					starts.add(new Pos(i, j));
			}
		}
		for (int i = 0; i < starts.size(); ++i) {
			Pos curPos = starts.get(i);
			if (inBound(rows, cols, curPos.x, curPos.y) && getPath(matrixNew, rows, cols, str, curPos, 0, curPos))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new Solution4().hasPath("ABCESFCSADEE".toCharArray(), 3, 4, "ABCB".toCharArray()));
		Solution4 solution = new Solution4();
		solution.visited.add(new Pos(1, 1));
		solution.visited.add(new Pos(1, 1));
		solution.visited.add(new Pos(1, 1));
		System.out.println(solution.visited.size());
	}
}
