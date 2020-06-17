package com.lithium;

import java.util.ArrayList;
import java.util.List;

public class LithAna {

	public static void main(String[] args) {

		int[] minimumDifference = getMinimumDifference(new String[] { "a", "jk", "abb", "mn", "abc" },
				new String[] { "bb", "kj", "bbc", "op", "def" });
		int[] minimumDifference2 = getMinimumDifference(new String[] { "abc", "aaa" }, new String[] { "bba", "bbb" });
		for (int i : minimumDifference) {
			System.out.println(i);
		}

	}

	private static int[] getMinimumDifference(String[] a, String[] b) {
		int[] arr = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (a[i].length() != b[i].length()) {
				arr[i] = -1;
				continue;
			}
			List<String> aList = convertToList(a[i]);
			List<String> bList = convertToList(b[i]);
			if (aList.equals(bList)) {
				arr[i] = 0;
				continue;
			}
			// List<String> difference = new ArrayList<>(aList);
			// difference.removeAll(bList);
			// arr[i] = difference.size();
			arr[i] = getDifference(aList, bList);
		}
		return arr;
	}

	private static List<String> convertToList(String string) {
		ArrayList<String> list = new ArrayList<>();
		for (char c : string.toCharArray()) {
			list.add("" + c);
		}
		return list;
	}

	private static int getDifference(List<String> a, List<String> b) {
		for (String string : b) {
			a.remove(string);
		}
		return a.size();
	}
}
