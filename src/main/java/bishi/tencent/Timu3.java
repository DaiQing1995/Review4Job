package bishi.tencent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 0% pass
 * @author DaiQing
 *
 */
public class Timu3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		for (int i = 0; i < N; ++i) {
			int t = scanner.nextInt();
			ArrayList<Integer> groupA = new ArrayList<>();
			ArrayList<Integer> groupB = new ArrayList<>();
			int countA = 0;
			int countB = 0;
			for (int j = 0; j < t; ++j) {
				int data = scanner.nextInt();
				groupA.add(data);
				countA += data;
			}
			Collections.sort(groupA);
			int chazhi = countA - countB;
			while (true) {
				int index = Collections.binarySearch(groupA, (chazhi) / 2);
				index = index < 0 ? -index - 1 : index;
				index = index == groupA.size() ? index - 1 : index;
				int tmpCTA = countA;
				int tmpCTB = countB;
				boolean flag = false;
				countA -= groupA.get(index);
				countB += groupA.get(index);
				if (index + 1 < groupA.size()) {
					tmpCTA -= groupA.get(index + 1);
					tmpCTB += groupA.get(index + 1);
					if (Math.abs(tmpCTA - tmpCTB) < Math.abs(countA - countB)) {
						flag = true;
						countA = tmpCTA;
						countB = tmpCTB;
					}
				}
				if (Math.abs(countA - countB) > chazhi) {
					countA += flag ? groupA.get(index + 1) : groupA.get(index);
					countB -= flag ? groupA.get(index + 1) : groupA.get(index);
					System.out.println(countA > countB ? countB + " " + countA : countA + " " + countB);
					break;
				}else {
					groupB.add(groupA.remove(index));
				}
				chazhi = countA - countB;
			}
		}
		scanner.close();
	}
}
