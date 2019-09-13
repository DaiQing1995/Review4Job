package bishi.bigo;

import java.util.LinkedList;

public class MergeList {
	
	/**
	 * 设数字已经表示到data（二进制） 
	 */
	public static void retVal(int[] data, int n) {
		int x = 0;
		int y = 0;
		if (n % 2 == 0)
			data[0] = 0;
		else
			data[0] = 1;
		for (int i = 1; i < data.length; ++ i) {
			if (i % 2 == 0) {
				data[i] = (x + i) % 2 == 0 ? 0 : 1;
				x ++;
			}else {
				data[i] = (y + i) % 2 == 0 ? 0 : 1;
				y ++;
			}
		}
	}
	
	public static LinkedList<Integer> merge(LinkedList<Integer> fst, LinkedList<Integer> snd){
		LinkedList<Integer> ret = new LinkedList<>();
		int i = 0;
		int j = 0;
		while(i < fst.size() && j < snd.size()) {
			if (fst.get(i) < snd.get(j)) {
				ret.add(fst.get(i ++));
			}else {
				ret.add(snd.get(j ++));
			}
		}
		while(i < fst.size()) {
			ret.add(fst.get(i ++ ));
		}
		while(j < snd.size()) {
			ret.add(snd.get(i ++ ));
		}
		return ret;
	}
}
