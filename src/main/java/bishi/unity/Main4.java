package bishi.unity;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		LinkedList<Long> men = new LinkedList<>();
		int[] ans = new int[n];
		for (int i = 0;i < n; ++ i) {
			men.addFirst(sc.nextLong());
		}
		ans[0] = 0;
		TreeMap<Long, Long> valueCount = new TreeMap<>();
		valueCount.put(men.get(0), (long) 1);
		for (int i = 1; i < men.size(); ++ i) {
			if (valueCount.containsKey(men.get(i))) {
				valueCount.put(men.get(i), valueCount.get(men.get(i)) + 1);
			}else {
				valueCount.put(men.get(i), (long) 1);
			}
			Set<Long> keySet = valueCount.keySet();
			int curAns = 0;
			for (Long value : keySet) {
				if (value >= men.get(i)) {
					ans[i] = curAns;
					break;
				}else {
					curAns += valueCount.get(value);
				}
			}
		}
		for (int i = ans.length - 1; i >= 0; -- i)
			System.out.print(ans[i] + " ");
		sc.close();
	}

}
