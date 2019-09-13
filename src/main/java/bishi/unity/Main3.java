package bishi.unity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] split = sc.nextLine().split(" ");
		char[] charArray = split[0].toCharArray();
		char[] resourceArray = split[1].toCharArray();
		HashMap<Character, Integer> chars = new HashMap<>();
		for (int i = 0; i < resourceArray.length; ++i) {
			if (chars.containsKey(resourceArray[i])) {
				chars.put(resourceArray[i], chars.get(resourceArray[i]) + 1);
			} else {
				chars.put(resourceArray[i], 1);
			}
		}
		for (int i = 0; i < charArray.length; ++i) {
			if (chars.containsKey(charArray[i])) {
				chars.put(charArray[i], chars.get(charArray[i]) - 1);
				if (chars.get(charArray[i]) < 0) {
					System.out.println("NO");
					return;
				}
			}else {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

}
