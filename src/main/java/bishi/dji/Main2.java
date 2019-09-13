package bishi.dji;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line;
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			String[] split = line.trim().split(" ");
			int N = Integer.parseInt(split[0]);
			int A = Integer.parseInt(split[1]);
			int X = Integer.parseInt(split[2]);
			int[] bugs = new int[N];
			int bugsTime = 0;
			line = sc.nextLine();
			split = line.trim().split(" "); 
			for (int i = 0;i < N; ++ i) {
				bugs[i] = Integer.parseInt(split[i]);
				bugsTime += bugs[i];
			}
			if (bugsTime < A * 60 * X) {
				int costTime = bugsTime / A;
				if (bugsTime % A != 0) {
					 costTime += 1;
				}
				System.out.println(costTime > 8 * 60 ? -1 : costTime);
			}else {
				int costTime = 60 * X;
				int remainTime = bugsTime - A * 60 * X;
				costTime += remainTime; 
				System.out.println(costTime > 8 * 60 ? -1 : costTime);
			}
		}
		sc.close();
	}
	
}
