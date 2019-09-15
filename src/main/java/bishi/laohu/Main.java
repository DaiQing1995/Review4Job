package bishi.laohu;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		Stack<Character> stack = new Stack<>();
		stack.push(input.charAt(0));
		if (input.length() % 2 != 0) {
			System.out.println("false");
			return;
		}
		int index = 1;
		while (!stack.isEmpty() && index < input.length()) {
			char ch = input.charAt(index);
			if (ch == '{' || ch == '[' || ch == '(') {
				stack.add(ch);
				index++;
				continue;
			}
			if (!stack.isEmpty() && stack.peek() == '(' && ch == ')')
				stack.pop();
			else if (!stack.isEmpty() && stack.peek() == '[' && ch == ']')
				stack.pop();
			else if (!stack.isEmpty() && stack.peek() == '{' && ch == '}')
				stack.pop();
			else {
				System.out.println("false");
				return;
			}
			index ++;
		}
		System.out.println("true");
	}
}
