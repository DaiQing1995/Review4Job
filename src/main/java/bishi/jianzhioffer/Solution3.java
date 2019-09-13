package bishi.jianzhioffer;

public class Solution3 {

	int BinararySearch(int[] array, int k, int from, int to) {
		if (from > to || to >= array.length || from < 0)
			return -1;
		int mid = (from + to) / 2;
		if (array[mid] == k)
			return mid;
		if (array[from] == k)
			return from;
		if (array[to] == k)
			return to;
		if (from == to && array[from] != k)
			return -1;
		if (from + to % 2 == 0) {
			int x = BinararySearch(array, k, from, mid);
			if (x != -1)
				return x;
			int y = BinararySearch(array, k, mid + 1, to);
			if (y != -1)
				return y;
		}
		return -1;
	}

	public int GetNumberOfK(int[] array, int k) {
		if (array.length == 0)
			return 0;
		int index = BinararySearch(array, k, 0, array.length - 1);
		if (index == -1)
			return 0;
		int ans = 0;
		for (int i = index; i < array.length && array[i] == k; ++i)
			ans++;
		for (int i = index - 1; i >= 0 && array[i] == k; --i)
			ans++;
		return ans;
	}

	public static void main(String[] args) {
		// System.out.println(new Solution().InversePairs(new int[]
		// {364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575}));
		System.out.println(new Solution3().GetNumberOfK(new int[] { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3,
				3, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 }, 3));
	}

}