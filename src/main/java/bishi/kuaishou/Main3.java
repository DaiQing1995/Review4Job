package bishi.kuaishou;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Main3 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; ++i) {
			int input = sc.nextInt();
			System.out.println(getResult(input));
		}
	}

	private static class Num{
		LinkedList<Integer> vals = new LinkedList<>();
		
		public Num(LinkedList<Integer> vals) {
			super();
			this.vals = vals;
		}

		public int hashCode() {
			int ret = 0;
			for (int i = 0;i < vals.size(); ++ i)
				ret += vals.get(i);
			return ret;
		}
		
		public boolean equals(Object obj) {
			Num tar = (Num)obj;
			if (tar.vals.size() != this.vals.size())
				return false;
			Collections.sort(tar.vals);
			Collections.sort(this.vals);
			for (int i = 0; i < this.vals.size(); ++ i) {
				if (this.vals.get(i) != tar.vals.get(i))
					return false;
			}
			return true;
		}

	}
	
	private static boolean getResult(int input) {
		Set<Num> set = new HashSet<>();
		LinkedList<Integer> curNum = getList(input);
		set.add(new Num(curNum));
		while(true) {
			int nxtVal = 0;
			for (int i = 0;i < curNum.size(); ++ i) {
				nxtVal += curNum.get(i) * curNum.get(i); 
			}
			if (nxtVal == 1)
				return true;
			LinkedList<Integer> nxtNumLst = getList(nxtVal);
			Num nxtNum = new Num(nxtNumLst);
			if (set.contains(nxtNum)) 
				return false;
			else 
				set.add(nxtNum);
			curNum = nxtNumLst;
		}
	}
	
	private static LinkedList<Integer> getList(int val){
		LinkedList<Integer> nums = new LinkedList<>();
		while(val > 0) {
			nums.add(val % 10);
			val /= 10;
		}
		return nums;
	}

}