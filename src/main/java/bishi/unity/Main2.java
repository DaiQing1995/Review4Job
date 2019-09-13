package bishi.unity;

import java.util.Scanner;

public class Main2 {

	// 1. 对王
	public static boolean isJoker(String[] args) {
		if (args.length == 2 && args[0].equals("joker") && args[1].equals("JOKER"))
			return true;
		if (args.length == 2 && args[1].equals("joker") && args[0].equals("JOKER"))
			return true;
		return false;
	}

	// 2. 炸弹
	public static boolean isBoom(String[] args) {
		if (args.length == 4 && args[0].equals(args[1]) && args[1].equals(args[2]) && args[2].equals(args[3]))
			return true;
		return false;
	}

	// 3. 三个
	public static boolean isThree(String[] args) {
		if (args.length == 3 && args[0].equals(args[1]) && args[1].equals(args[2]))
			return true;
		return false;
	}

	// 4. 顺子
	public static boolean isSeq(String[] args) {
		if (args.length == 5) {
			for (int i = 1; i < 5; ++i) {
				if (Integer.parseInt(args[i]) - 1 != Integer.parseInt(args[i - 1]))
					return false;
			}
		}else
			return false;
		return true;
	}

	// 5. 对子
	public static boolean isTwo(String[] args) {
		if (args.length == 2 && args[0].equals(args[1]))
			return true;
		return false;
	}

	// 6. 个子
	public static boolean isOne(String[] args) {
		if (args.length == 1)
			return true;
		return false;
	}

	public static void PrintAns(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; ++i)
			sb.append(args[i] + " ");
		String ret = sb.toString();
		ret = ret.replaceAll("11", "J");
		ret = ret.replaceAll("12", "Q");
		ret = ret.replaceAll("13", "K");
		ret = ret.replaceAll("14", "A");
		ret = ret.replaceAll("15", "2");
		System.out.println(ret);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nextLine = sc.nextLine();
		nextLine = nextLine.replaceAll("joker", "mm");
		nextLine = nextLine.replaceAll("JOKER", "MM");
		nextLine = nextLine.replaceAll("2", "15");
		nextLine = nextLine.replaceAll("J", "11");
		nextLine = nextLine.replaceAll("Q", "12");
		nextLine = nextLine.replaceAll("K", "13");
		nextLine = nextLine.replaceAll("A", "14");
		nextLine = nextLine.replaceAll("mm", "joker");
		nextLine = nextLine.replaceAll("MM", "JOKER");
		String[] split = nextLine.split("-");
		if (split.length != 2) {
			System.out.println("ERROR");
			return;
		}
		String[] first = split[0].split(" ");
		String[] second = split[1].split(" ");
		if (first.length == 0 || second.length == 0) {
			System.out.println("ERROR");
			return;
		}
		if (isJoker(first)) {
			PrintAns(first);
			return;
		} else if (isJoker(second)) {
			PrintAns(second);
			return;
		}
		if (containsJoker(first) || containsJoker(first)) {
			System.out.println("ERROR");
			return;
		}
		if (isBoom(first)) {
			if (isBoom(second)) {
				if (Integer.parseInt(first[0]) < Integer.parseInt(second[0])) {
					PrintAns(second);
				} else {
					PrintAns(first);
				}
				return;
			} else {
				PrintAns(first);
				return;
			}
		} else if (isBoom(second)) {
			PrintAns(second);
			return;
		}
		if (isOne(first)) {
			if (isOne(second)) {
				if (first[0].equalsIgnoreCase("joker")) {
					PrintAns(first);
				} else if (second[0].equalsIgnoreCase("joker")) {
					PrintAns(second);
				} else if (Integer.parseInt(first[0]) < Integer.parseInt(second[0])) {
					PrintAns(second);
				} else {
					PrintAns(first);
				}
			} else {
				System.out.println("ERROR");
			}
			return;
		}
		if (isSeq(first)) {
			if (isSeq(second)) {
				if (Integer.parseInt(first[0]) < Integer.parseInt(second[0])) {
					PrintAns(second);
				} else {
					PrintAns(first);
				}
				return;
			} else {
				System.out.println("ERROR");
				return;
			}
		}
		if (isThree(first)) {
			if (isThree(second)) {
				if (Integer.parseInt(first[0]) < Integer.parseInt(second[0])) {
					PrintAns(second);
				} else {
					PrintAns(first);
				}
			} else {
				System.out.println("ERROR");
			}
			return;
		}
		if (isTwo(first)) {
			if (isTwo(second)) {
				if (Integer.parseInt(first[0]) < Integer.parseInt(second[0])) {
					PrintAns(second);
				} else {
					PrintAns(first);
				}
			} else {
				System.out.println("ERROR");
			}
			return;
		}
		System.out.println("ERROR");
	}

	private static boolean containsJoker(String[] first) {
		if (first.length == 1)
			return false;
		for (int i = 0; i < first.length; ++i)
			if (first[i].equals("joker") || first[i].equals("JOKER"))
				return true;
		return false;
	}
}
