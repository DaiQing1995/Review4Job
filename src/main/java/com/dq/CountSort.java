package com.dq;

public class CountSort{

	private static final int UPPER_BOUND = 1000;

	public int[] sort(int[] data){
		int[] base = new int[UPPER_BOUND];
		for (int i = 0;i < data.length; ++ i){
			base[data[i]] ++;
		}
		int count = 0;
		for (int i = 0;i < UPPER_BOUND; ++ i){
			while (base[i] -- > 0) data[count ++] = i;
		}
		return data;
	}

	public static void main(String[] args){
		int[] data = {21,4,3,3,6,7,8,897,65,4,6,32,2,43,65,6,7,43,42,2,32,5,5,623,67,52,78,43,32};
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		new CountSort().sort(data);
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
	}
}
