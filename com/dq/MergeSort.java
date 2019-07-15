package com.dq;

public class MergeSort{
	
	public int[] sort(int[] data){
		if (data.length == 1)
			return data;
		else if (data.length == 2){
			if (data[1] < data[0]){
				int tmp = data[1];
				data[1] = data[0];
				data[0] = tmp;
			}
		}
	}

}
