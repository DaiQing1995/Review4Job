package bishi;

public class BinarySearch{
	
	public static int binararySearch(int[] data, int from, int to, int target) {
		if (from == to) {
			if (data[from] != target)
				return -1;
			else
				return from;
		}
		int mid = (from + to) / 2;
		if (data[mid] == target)
			return mid;
		if (data[mid] < target) {
			return binararySearch(data, mid + 1, to, target); 
		}else {
			return binararySearch(data, from, mid - 1, target);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(1 + binararySearch(new int[] {11, 13, 15, 17, 19, 21}, 0 , 5, 19));
	}
}