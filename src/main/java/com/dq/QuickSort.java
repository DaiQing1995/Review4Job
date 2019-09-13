package com.dq;

public class QuickSort{

	public int partition(int[] data, int p, int r) {
		int tmp = data[p];
		while (p < r) {
			while (p < r && data[r] >= tmp)
				r--;
			data[p] = data[r];
			while (p < r && data[p] <= tmp)
				p++;
			data[r] = data[p];
		}
		data[p] = tmp;
 		return p;
	}

	public void qSort(int[] data, int p, int r){
		int q;
		if (p < r){
			q = partition(data, p, r);
		}else{
			return;
		}
		qSort(data, q + 1, r);
		qSort(data, p, q - 1);
	}

	public int[] sort(int[] data){
		qSort(data, 0, data.length - 1);
		return data;
	}

	public static void main(String[] args){
		int[] data = {21,43,456,234,6,1,87,98,4,0,42,42,76,234,-43};
		for (int i = 0;i < data.length; ++ i){
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		}
		new QuickSort().sort(data);
		for (int i = 0;i < data.length; ++ i){
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		}
	}
}
