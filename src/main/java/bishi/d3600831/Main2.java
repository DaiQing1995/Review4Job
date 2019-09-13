package bishi.d3600831;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String data = scanner.nextLine();
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		for (int i = 0;i < data.length(); ++ i) {
			char ch = data.charAt(i);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			}else {
				map.put(ch, 1);
			}
			if (map.get(ch) > max)
				max = map.get(ch);
		}
		System.out.println(max);
		scanner.close();
	}
}
