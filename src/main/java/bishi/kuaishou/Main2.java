package bishi.kuaishou;

import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; ++i) {
			String[] sp = sc.nextLine().split(" ");
			if (canUpdate(sp[0], sp[1]))
				System.out.println(true);
			else
				System.out.println(false);
		}
	}

	private static boolean canUpdate(String pre1, String nxt1) {
		String[] pre = pre1.split("\\.");
		String[] nxt = nxt1.split("\\.");
		int preIndex = 0;
		int nxtIndex = 0;
		boolean flag = true;
		int preVal = Integer.parseInt(pre[preIndex]);
		int nxtVal = Integer.parseInt(nxt[nxtIndex]);
		int preCount = 0;
		int nxtCount = 0;
		while (flag) {
			if (preVal > nxtVal)
				return false;
			else if (preVal < nxtVal)
				return true;
			preIndex++;
			nxtIndex++;
			if (preIndex < pre.length) {
				preVal = Integer.parseInt(pre[preIndex]);
			} else {
				preVal = 0;
				preCount++;
			}
			if (nxtIndex < nxt.length) {
				nxtVal = Integer.parseInt(nxt[nxtIndex]);
			} else {
				nxtVal = 0;
				nxtCount++;
			}
			if (preCount >= 2 && nxtCount >= 2)
				flag = !flag;
		}
		return false;
	}

}