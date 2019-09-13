package com.dq;

public class SelectSort{
	public int[] sort(int[] data){
		for(int i = 0; i < data.length - 1; ++ i){
			int min = data[i];
			int minIndex = i;
			for (int j = i;j < data.length; ++ j){
				if (min > data[j]){
					min = data[j];
					minIndex = j;
				}
			}
			int tmp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = tmp;
		}
		return data;
	}

	public static void main(String[] args){
		int[] data = {421,21,52,5,4,12,5,876,23,1,612,64,2};
		for (int i = 0; i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		new SelectSort().sort(data);
		for (int i = 0; i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
	}
}
