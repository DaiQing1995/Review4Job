
public class QuickSort {
	
	public static void Qsort(int[] data, int from, int to) {
		if (from >= to)
			return;
		int p = partition(data, from, to);
		Qsort(data, 0, p - 1);
		Qsort(data, p + 1, to);
	}

	private static int partition(int[] data, int from, int to) {
		int pivot = data[from];
		while(from < to) {
			while(from < to && data[to] >= data[from]) to --;
			data[from] = data[to];
			while(from < to && data[to] >= data[from]) from ++;
			data[to] = data[from];
		}
		data[to] = pivot;
		return to;
	}
	
	public static void main(String[] args) {
		int[] data = new int[]{1,43,12,4,12,5,4,52,3,21,65,7647,568,76,4,532,2234,46,54,8,32};
		Qsort(data, 0, data.length - 1);
		for (int i : data) {
			System.out.print(i + " ");
		}
	}
}
