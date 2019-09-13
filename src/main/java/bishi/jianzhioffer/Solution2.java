package bishi.jianzhioffer;

public class Solution2 {
    
    public static int ans = 0;
    
    public void swap(int[] array, int start, int end) {
    	int tmp = array[start];
    	array[start] = array[end];
    	array[end] = tmp;
    }
    
    public void mergeSort(int[] array, int start, int end) {
    	if (end <= start || start < 0 || end >= array.length)
    		return;
    	if (end - start <= 1) {
    		if (array[start] > array[end]) {
    			ans ++;
    			swap(array, start, end);
    			return;
    		}
    	}
    	int mid = (start + end) / 2;
    	mergeSort(array, start, mid);
    	mergeSort(array, mid + 1, end);
    	merge(array, start, mid + 1, end);
    }
    
    private void merge(int[] array, int start, int mid, int end) {
    	int i = start;
    	int j = mid;
    	int[] tmpData = new int[end - start + 1];
    	int count = 0;
    	while(i < mid && j <= end) {
    		if (array[i] > array[j]) {
    			tmpData[count ++] = array[j];
    			ans += mid - i;
    			j ++;
    		}else {
    			tmpData[count ++] = array[i];
    			i ++;
    		}
    	}
    	while(i < mid) {
			tmpData[count ++] = array[i ++];
    	}
    	while(j <= end) {
			tmpData[count ++] = array[j ++];
    	}
    	System.arraycopy(tmpData, 0, array, start, end - start + 1);
        ans %= 1000000007;
	}

	public int InversePairs(int [] array) {
        if (array.length <= 1)
            return 0;
        mergeSort(array, 0, array.length - 1);
        return ans;
    }
    
    public static void main(String[] args) {
    	//System.out.println(new Solution().InversePairs(new int[] {364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575}));
    	System.out.println(new Solution2().InversePairs(new int[] {1, 3, 6, 7, 4, 2, 8, 10}));
    }
    
}