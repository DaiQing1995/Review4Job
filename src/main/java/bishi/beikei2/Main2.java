package bishi.beikei2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int S = sc.nextInt();
		ArrayList<Integer> arr = new ArrayList<>();
		sc.nextLine();
		for (int i = 0;i < n; ++ i) {
			arr.add(sc.nextInt());
		}
		Collections.sort(arr);
		for (int i = 0;i < arr.size(); ++ i) {
			S = S - arr.get(i);
			if (S < 0) {
				System.out.println(i);
				return;
			}
		}
	}
}
