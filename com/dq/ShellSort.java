package com.dq;

public class ShellSort{
	public int[] sort(int[] data){
		int step = data.length / 2;
//		int count = 0;
		while(step != 0){
			for (int i = 1; i < data.length; i ++){
				int j = i;
				int tmp = data[j];
				while (j - step >= 0 && tmp < data[j - step]){
					data[j] = data[j - step];
					j -= step;
				}
				data[j] = tmp;
			}
			step = step / 2;

//			System.out.println("the " + ++count + " times result(step:" + step + "):");
//			for (int i = 0; i < data.length; ++ i)
//				System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		}
		return data;
	}

	public static void main(String[] args){
		int[] data = {21,54,13,6,31,65,1234,87,21,65,26,51,73,512};
		for (int i = 0; i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		new ShellSort().sort(data);
		for (int i = 0; i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
	}
}
