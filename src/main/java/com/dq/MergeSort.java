package com.dq;

public class MergeSort{
	
	public int[] merge(int[] data, int low, int high, int middle){
		int lowerBound = low;
		int upperBound = middle;
		int[] tmpData = new int[high - low + 1];
		int tmpDataIndex = 0;
		while (lowerBound < middle && upperBound <= high){
			if (data[lowerBound] <= data[upperBound]){
				tmpData[tmpDataIndex ++] = data[lowerBound ++];
			}else{
				tmpData[tmpDataIndex ++] = data[upperBound ++];
			}
		}
		while(lowerBound < middle){
			tmpData[tmpDataIndex ++] = data[lowerBound ++];
		}
		while(upperBound <= high){
			tmpData[tmpDataIndex ++] = data[upperBound ++];
		}
		for (int i = 0; i < tmpData.length; ++i){
			data[low + i] = tmpData[i];
		}
		return data;
	}

	public int[] sort(int[] data, int high, int low){
		if (high <= low)
			return data;
		else if (high - 1 == low){
			if (data[high] < data[low]){
				int tmp = data[low];
				data[low] = data[high];
				data[high] = tmp;
			}
			return data;
		}
		int middle = (high + low) / 2;
		sort(data, middle - 1, low);
		sort(data, high, middle);
		merge(data, low, high, middle);
		return data;
	}

	public static void main(String[] args){
		int[] data = {23,54,2,6,12,6754,865,124,675,36,76,328,53};
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		new MergeSort().sort(data, data.length - 1, 0);
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
	}

}
