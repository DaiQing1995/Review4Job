package bishi.shunfeng;

import java.util.Arrays;

public class Solution {
	public int lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		int len = 0;
		for (int num : nums) {
			int i = Arrays.binarySearch(dp, 0, len, num);
			if (i < 0) {
				i = -(i + 1);
			}
			dp[i] = num;
			if (i == len) {
				len++;
			}
			for (int k = 0; k < len; ++k)
				System.out.print(dp[k] + " ");
			System.out.println();
		}
		return len;
	}

	public static void main(String[] args) {
    	System.out.println(new Solution().lengthOfLIS(new int[] {0, 8, 4, 12, 2}));
    	
    	int[] data = new int[] {2,3,5,7};
    	
    	int x = Arrays.binarySearch(data, 0, 4, 7);
    	if (x < 0)
    		x = -x - 1;
    	data[x] = 11000;
    	for (int k = 0; k < 4; ++k)
			System.out.print(data[k] + " ");
		System.out.println();
    }
}