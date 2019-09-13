package bishi.yongyou;

import java.util.Scanner;

/**
 * 高精度乘法
 * @author DaiQing
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] split = sc.nextLine().split(",");
		if (split[0].equals("0") || split[1].equals("0")) {
			System.out.println(0);
			return;
		}
		if (split[0].length() > split[1].length())
			System.out.println(getVal(split[0], split[1]));
		else
			System.out.println(getVal(split[1], split[0]));
		sc.close();
	}

	private static String getVal(String fst, String snd) {
		int i = fst.length() - 1;
		int j = snd.length() - 1;
		int jinwei = 0;
		int count = 0;
		String lastStr = "";
		while (i >= 0) {
			StringBuilder sb = new StringBuilder();
			for (j = snd.length() - 1; j >= 0; j--) {
				int multiResult = (snd.charAt(j) - '0') * (fst.charAt(i) - '0') + jinwei;
				int val = multiResult % 10;
				jinwei = multiResult / 10;
				sb.insert(0, val);		
			}
			for (int k = 0; k < count; ++ k) {
				sb.append("0");
			}
			lastStr = add2Str(lastStr, sb.toString());
			i --;
			count ++;
		}
		return lastStr.toString();
	}

	private static String add2Str(String lastStr, String string) {
		String fst;
		String snd;
		if (lastStr.length() > string.length()) {
			fst = lastStr;
			snd = string;
		}else {
			fst = string;
			snd = lastStr;
		}
		int i = fst.length() - 1;
		int j = snd.length() - 1;
		int jinwei = 0;
		StringBuilder sb = new StringBuilder();
		while(i >= 0 && j >= 0) {
			int curAdd = (fst.charAt(i) - '0') + (snd.charAt(j) - '0') + jinwei;
			sb.insert(0, curAdd % 10);
			jinwei = curAdd / 10;
			i --;
			j --;
		}
		while (i >= 0) {
			int curAdd = fst.charAt(i) - '0' + jinwei;
			sb.insert(0, curAdd % 10);
			jinwei = curAdd / 10;
			i --;
		}
		if (j > 0) {
			int curAdd = snd.charAt(j) - '0' + jinwei;
			sb.insert(0, curAdd % 10);
			jinwei = curAdd / 10;
			j --;
		}
		if (jinwei != 0)
			sb.insert(0, jinwei);
		return sb.toString();
	}
}
