package bishi;

import java.util.*;

public class Solution2 {

	static class NCharacter implements Comparable<NCharacter> {
		char c;
		int times;
		int index;

		public NCharacter(char c, int index, int times) {
			this.c = c;
			this.times = times;
			this.index = index;
		}

		public int hashCode() {
			return c;
		}

		public boolean equals(Object b) {
			NCharacter b1 = (NCharacter) b;
			if (b1.c == this.c) {
				return true;
			} else {
				return false;
			}
		}

		public int compareTo(NCharacter m) {
			if (this.times > m.times) {
				return 1;
			} else if (this.times < m.times) {
				return -1;
			} else {
				if (this.index < m.index)
					return -1;
				else if (this.index > m.index)
					return 1;
				else
					return 0;
			}
		}
	}

	static PriorityQueue<NCharacter> pQueue = new PriorityQueue<>();

	public int FirstNotRepeatingChar(String str) {
		HashMap<Character, Integer> chars = new HashMap<>();
		HashMap<Character, Integer> charsIndex = new HashMap<>();
		for (int i = 0; i < str.length(); ++i) {
			if (!chars.containsKey(str.charAt(i))) {
				chars.put(str.charAt(i), 1);
				charsIndex.put(str.charAt(i), i);
			} else {
				chars.put(str.charAt(i), chars.get(str.charAt(i)) + 1);
			}
		}
		Set<Character> keys = chars.keySet();
		for (Character k : keys)
			pQueue.add(new NCharacter(k, charsIndex.get(k), chars.get(k)));
		NCharacter ch = pQueue.poll();
		int ans = ch.times == 1 ? ch.index : -1;
		return ans;
	}

	/*
	 * 链接：https://www.nowcoder.com/questionTerminal/1c82e8cf713b4bbeb2a5b31cf5b0417c
	 * ?f=discussion 来源：牛客网
	 * 
	 * int[] a = new int[58]; for(int i=0; i<str.length(); i++) { a[str.charAt(i) -
	 * 'A'] += 1; } for(int j=0; j<str.length(); j++) { if(a[str.charAt(j) - 'A'] ==
	 * 1) { return j; } } return -1;
	 */
	public static void main(String[] args) {

		HashMap<Character, Integer> chars = new HashMap<>();
		NCharacter nCharacter = new NCharacter('c', 1, 1);
		NCharacter nCharacter2 = new NCharacter('c', 1, 1);
		NCharacter nCharacter3 = new NCharacter('c', 1, 1);
		chars.put('c', 1);
		chars.put('c', 1);
		chars.put('c', 1);
		System.out.println(nCharacter.hashCode());
		System.out.println(nCharacter2.hashCode());
		System.out.println(nCharacter3.hashCode());
		System.out.println(chars.size());

		System.out.println(
				new Solution2().FirstNotRepeatingChar("kYVmIJVzYWPQLtIdKMmvkVSoKtqJANOfCCOfLVJEjjhbnPrDOwKCDeqhts"));
	}
}