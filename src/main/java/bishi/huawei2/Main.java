package bishi.huawei2;

import java.util.*;

public class Main {

	public static int canBecome(String source, String target) {
		if (source.contains(target))
			return 1;
		StringBuilder builder = new StringBuilder(target);
		int index;
		for (int i = 0; i < target.length(); ++i) {
			if (-1 != (index = source.lastIndexOf(builder.toString()))) {
				int remainTargetSum = target.length() - builder.length();
				if (source.substring(index, source.length()).equals(target.subSequence(0, target.length() - remainTargetSum))){
					int ret = 1;
					for (int j = 0; j < remainTargetSum; ++j) {
						if (source.charAt(j) != target.charAt(builder.length() + j))
							ret = 0;
					}
					if (ret == 1)
						return ret;
				}
			}
			builder.deleteCharAt(builder.length() - 1);
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ans = new int[3];
		for (int i = 0; i < 3; ++i) {
			String source = sc.nextLine();
			String target = sc.nextLine();
			ans[i] = canBecome(source, target);
		}
		for (int i = 0; i < 3; ++i)
			System.out.print(ans[i]);
		sc.close();
	}

	// public static void main(String[] args) {
	// Scanner sc = new Scanner(System.in);
	// int[] ans = new int[3];
	// for (int i = 0;i < 3; ++ i) {
	// String source = sc.nextLine();
	// String target = sc.nextLine();
	//
	// StringBuilder sBuilder = new StringBuilder(source);
	// for (int j = 0;j < source.length(); ++ j) {
	// sBuilder.append(sBuilder.charAt(0));
	// sBuilder.deleteCharAt(0);
	// if (sBuilder.toString().contains(target))
	// ans[i] = 1;
	// }
	// }
	// sc.close();
	// for (int i = 0;i < 3; ++ i)
	// System.out.print(ans[i]);
	// }
}
