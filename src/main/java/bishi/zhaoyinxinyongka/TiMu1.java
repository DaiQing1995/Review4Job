package bishi.zhaoyinxinyongka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * LRLRLRLR在10^5次运动，最后统计数目
 * AC
 * @author DaiQing
 */
public class TiMu1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] charArray = sc.nextLine().toCharArray();
		int[] result = new int[charArray.length];
		ArrayList<Integer> tmpArr = new ArrayList<>();
		for (int i = 0; i < charArray.length; ++i) {
			int start = i;
			tmpArr.clear();
			int startLoopIndex;
			boolean startFlag = charArray[start] == 'R' ? true : false;
			boolean flag = false;
			while (true) {
				tmpArr.add(start);
				if (charArray[start] == 'R') {
					start++;
					flag = false;
				} else if (charArray[start] == 'L') {
					start--;
					flag = true;
				}
				if ((startLoopIndex = Collections.binarySearch(tmpArr, start)) >= 0) {
					break;
				}
			}
			int tmpAns = tmpArr.size() % 2 == 0 ? 0 : 1;
			if (flag) {
				if (startFlag)
					result[i + startLoopIndex + tmpAns]++;
				else
					result[i - startLoopIndex - tmpAns]++;
			} else {
				if (startFlag)
					result[i + startLoopIndex + tmpAns]++;
				else
					result[i - startLoopIndex - tmpAns]++;
			}
		}
		for (int i = 0; i < result.length; ++i)
			System.out.print(result[i] + " ");
		System.out.println();
		sc.close();
	}
}
