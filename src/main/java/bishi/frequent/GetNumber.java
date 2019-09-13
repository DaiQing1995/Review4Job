package bishi.frequent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GetNumber{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String raw = sc.nextLine().trim();
		ArrayList<Integer> nums = new ArrayList<>();
		for (int i = 0; i < raw.length(); ++ i) {
			int ch = raw.charAt(i) - '0';
			if (ch >=0 && ch <= 9)
				nums.add(ch);
		}
		Collections.sort(nums);
		for (int i = 0; i < nums.size(); ++ i)
			System.out.print(nums.get(i));
	}
}