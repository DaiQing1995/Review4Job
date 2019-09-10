package com.dq;

public class InsertionSort{
	public int[] sort(int[] data){
		for (int i = 1; i < data.length; ++ i){
			int val = data[i];
			int j = i;
			while(j > 0 && val < data[j - 1]){
				data[j] = data[j - 1]; 
				j --;
			}
			data[j] = val;
		}
		return data;
	}

	public static void main(String[] args){
		int[] data = {42,123,542,543,21,343,65,873,23,76,27,41};
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + ( i == data.length - 1 ? "\n" : " "));
		new InsertionSort().sort(data);
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + ( i == data.length - 1 ? "\n" : " "));
	}
}
