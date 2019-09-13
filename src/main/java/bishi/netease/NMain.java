package bishi.netease;

import java.util.Scanner;

public class NMain {
	
	private static class Data{
		
		int aVal;
		int bVal;
		int aMin;
		
		public Data(int[] values, int[] status) {
			aMin = Integer.MAX_VALUE;
			aVal = 0;
			bVal = 0;
			for (int i = 0; i < status.length; ++ i) {
				if(status[i] == 0) {
					aVal += values[i];
					if(aMin > values[i]) {
						aMin = values[i];
					}
				}else {
					bVal += values[i];
				}
			}
			aMin = aMin == Integer.MAX_VALUE ? 0 : aMin;
		}
		
		public boolean isAvailable() {
			if (aVal > bVal && (aVal - bVal) < aMin * 2)
				return true;
			else
				return false;
		}
		
//		private int getAmin(int[] values, int[] status) {
//			int ret = Integer.MAX_VALUE;
//			for (int i = 0; i < values.length; ++i) {
//				if (status[i] == 0)
//					ret = values[i] < ret ? values[i] : ret;
//			}
//			return ret == Integer.MAX_VALUE ? 0 : ret;
//		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] values = new int[n];
		int[] status = new int[n];
		int AVal = 0;
		int BVal = 0;
		for (int i = 0; i < n; ++i) {
			values[i] = sc.nextInt();
			status[i] = 0;
		}
		int ans = 0;
		int curVal = 0;
		for (int i = 0;i < (int)Math.pow(2, n); ++ i) {
			int tmp = curVal;
			for (int j = 0; j < n; ++ j) {
				status[j] = tmp & 1;
				tmp >>>= 1;
			}
			ans += new Data(values, status).isAvailable() ? 1 : 0;
			curVal += 1;
		}
		System.out.println(ans);
	}
}
