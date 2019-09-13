package com.dq;

public class BubbleSort{

	public int[] sort(int[] data){
		for(int i = 1; i < data.length; ++ i){
			for (int j = 0; j < data.length - i; ++ j){
				if (data[j] > data[j + 1]){
					int tmp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = tmp;
				}
			}
		}
		return data;
	}


	public static void main(String[] args){
		int[] data = {2,1,4,5,6,3,2,12,54,21,6,7,1};
		for (int i = 0; i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		new BubbleSort().sort(data);
		
		for (int i = 0; i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
	}
}
