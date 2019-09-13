package bishi.huawei0807;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nextLine = sc.nextLine();
		String[] split = nextLine.split("@");
		String s1 = split[0];
		String s2 = split[1];
		String[] words = s1.split(",");
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; ++i) {
			String[] split2 = words[i].split(":");
			map.put(split2[0], Integer.parseInt(split2[1]));
		}
		
		
		words = s2.split(",");
		for (int i = 0; i < words.length; ++i) {
			String[] split2 = words[i].split(":");
			map.put(split2[0], (map.get(split2[0]) - Integer.parseInt(split2[1])));
		}
		

		words = s1.split(",");
		for (int i = 0; i < words.length; ++i) {
			String[] split2 = words[i].split(":");
			if (map.get(split2[0]) > 0) {
				System.out.print(i != words.length - 1
						? split2[0] + ":" + map.get(split2[0]) + ","
						: split2[0] + ":" + map.get(split2[0]) + "\n");
			}
		}
	}

}
