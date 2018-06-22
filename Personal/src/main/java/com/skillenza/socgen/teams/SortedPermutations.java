package com.skillenza.socgen.teams;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SortedPermutations {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		short testCases = scanner.nextShort();
		scanner.nextLine();
		for (byte i = 0; i < testCases; i++) {
			String testCase = scanner.nextLine().trim();
			Set<String> set = new TreeSet<>();
			getAnagrams(testCase.toCharArray(), (byte) 0, (byte) (testCase.length() - 1), set);
			printer(set);
		}
		scanner.close();
	}

	private static void getAnagrams(char[] array, byte start, byte end, Set<String> set) {
		if (start == end)
			set.add(String.valueOf(array));
		else {
			for (byte i = start; i <= end; i++) {
				swap(array, start, i);
				getAnagrams(array, (byte) (start + 1), end, set);
				swap(array, start, i);
			}
		}
	}

	private static void swap(char[] array, byte from, byte to) {
		char temp = array[from];
		array[from] = array[to];
		array[to] = temp;
	}

	private static void printer(Set<String> set) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : set)
			stringBuilder.append(string).append(' ');
		System.out.println(stringBuilder);
	}

}
