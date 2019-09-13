package bishi.vipkid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main2 {
	
	private static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj1) {
			Pair obj = (Pair)obj1;
			if (x == obj.x && y == obj.y) {
				return true;
			}else if (x == obj.y && y == obj.x) {
				return true;
			}
			return false;
		}
		@Override
		public int hashCode() {
			return x + y;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] split = scanner.nextLine().split(",");
		int[] data = new int[split.length];
		for (int i = 0; i < data.length; ++i) {
			data[i] = Integer.parseInt(split[i].trim());
		}
		Arrays.sort(data);
		Set<Pair> pairs = new HashSet<>();
		int ans = 0;
		for (int i = 0; i < data.length - 1; ++ i) {
			for (int j = i + 1; j < data.length; ++ j) {
				Pair pair = new Pair(data[i], data[j]);
				if (data[i] + data[j] == 0 && !pairs.contains(pair)) {
					ans ++;
					pairs.add(pair);
				}else if (data[i] + data[j] > 0)
					break;
			}
		}
		System.out.println(ans);
		scanner.close();
	}
}
/**
int i = 0;
int j = data.length - 1;
int ans = 0;
while (i != j) {
	if (data[i] + data[j] == 0) {
		ans++;
		i++;
	} else if (data[i] + data[j] < 0) {
		i++;
	} else {
		j--;
	}
}
System.out.println(ans);
*/