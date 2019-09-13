package bishi.yuanfudao;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < n; ++i) {
			String word = scanner.nextLine();
			System.out.println(getString(word, 0, word.length() - 1));
		}
		scanner.close();
	}

	private static String getString(String word, int from, int to) {
		StringBuilder stringBuilder = new StringBuilder();
		int currentIndex = from;
		while (currentIndex <= to) {
			if (word.charAt(currentIndex) == '(') {
				int tmpIndex = currentIndex + 1;
				Stack<Character> stack = new Stack<>();
				stack.add('(');
				while(!stack.isEmpty()) {
					currentIndex ++;
					if (word.charAt(currentIndex) == ')')
						stack.pop();
					if (word.charAt(currentIndex) == '(')
						stack.add('(');
				}
				String blanketContent = getString(word, tmpIndex, currentIndex - 1);
				currentIndex ++;
				int count = readNumber(currentIndex, word);
				for (int i = 0;i < count; ++ i)
					stringBuilder.append(blanketContent);
			}else if (word.charAt(currentIndex) >= 'A' && word.charAt(currentIndex) <= 'Z') {
				stringBuilder.append(word.charAt(currentIndex));
				char tmp = word.charAt(currentIndex);
				currentIndex ++;
				int count = readNumber(currentIndex, word);
				for (int i = 0;i < count - 1; ++ i)
					stringBuilder.append(tmp);
			}else {
				currentIndex ++;
			}
		}
		return stringBuilder.toString();
	}

	private static int readNumber(int currentIndex, String word) {
		ArrayList<Integer> number = new ArrayList<>();
		while (currentIndex < word.length()) {
			if(word.charAt(currentIndex) >= '0'&& word.charAt(currentIndex) <= '9') {
				number.add(word.charAt(currentIndex ++) - '0');
			}else {
				break;
			}
		}
		int ret = 0;
		int count = 1;
		for (int i = number.size() - 1; i >= 0; i --) {
			ret += number.get(i) * count;
			count *= 10;
		}
		return ret;
	}

}
