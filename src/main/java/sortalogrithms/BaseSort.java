package sortalogrithms;

import java.util.ArrayList;
import java.lang.Integer;

public class BaseSort{

	private static final int UPPER_BOUND = 8;

	private ArrayList<Integer>[] blankets = new ArrayList[10];

	public BaseSort(){
		for (int i = 0;i < 10; ++ i){
			blankets[i] = new ArrayList<>();
		}
	}

	public int[] sort(int[] data){
		int tmp, curNum;
		boolean flag = true;
		int times = 0;
		while(flag){
			flag = false;
			for (int i = 0; i < data.length; ++ i){
				tmp = data[i];
				for (int j = 0;j < times; ++j) tmp /= 10;
				if (tmp != 0) flag = true;
				curNum = tmp % 10;
				blankets[curNum].add(data[i]);
			}
			times ++;
			int count = 0;
			for (int i = 0;i < 10; ++ i){
				for (int j = 0; j < blankets[i].size(); ++ j){
					data[count ++] = blankets[i].get(j);
				}
				blankets[i].clear();
			}
		}
		return data;
	}

	public static void main(String[] args){
		int[] data = {1453128,3,42888,85,4,2691635,47,4242354,65,562,97543,4,23,44123,54,54,56,7,12563,456,72,32,6,234,15,78,98,7689,76,45};
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
		new BaseSort().sort(data);
		for (int i = 0;i < data.length; ++ i)
			System.out.print(data[i] + (i == data.length - 1 ? "\n" : " "));
	}
}
