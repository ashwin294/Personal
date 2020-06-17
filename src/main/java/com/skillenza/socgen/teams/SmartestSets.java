package com.skillenza.socgen.teams;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/*Think you are smart?

A smart-set is a set of distinct numbers in which all the elements have the same number of 1s in their binary form. The set of all smallest elements from each smart-set
that can be formed from a given array of distinct positive numbers is known as the smartest-set.

So given an array of distinct numbers, outline the elements of the smartest-set in ascending sorted order.
Example

Let the array be {6 , 2 , 11 , 1 , 9 , 14 , 13 , 4 , 18}.
In binary form the set is {110, 010, 1011, 0001, 1001, 1110, 1101, 0100, 10010}.
The smart-sets are {1, 2, 4}, {6, 9, 18}, {11, 13, 14}.

The smartest-set is {1,6,11} as each element is the smallest element from each smart-set.

Input Format

The first line of input consists of an integer t. This is the number of test cases. For each test case,
the first line of input contains an integer n. Here n is the number of elements in the array. The next line contains n space separated distinct integers which are the elements
of the array.

Output Format

The output will space separated integer elements of the smartest-set in ascending order.

Constraints

0 < t < 1000 (This is the number of test cases
2 < n < 10000 (This is the number of integer elements of the array)
1 < Xi < 100000 (This is the size of each element of the array)
Sample input :

3
9
6 2 11 1 9 14 13 4 18 
3
7 3 1
3
1 2 4

Sample Output :

1 6 11
1 3 7
1
*/

public class SmartestSets {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Map<Integer, TreeSet<Integer>> map = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			short testCases = scanner.nextShort();
			scanner.nextLine();
			for (short i = 0; i < testCases; i++) {
				int testCaseItems = scanner.nextInt();
				scanner.nextLine();
				map = new TreeMap<>();
				for (short j = 0; j < testCaseItems; j++) {
					int testCaseItem = scanner.nextInt();
					add(testCaseItem, map);
				}
				scanner.nextLine();
				stringBuilder.append(getString(map));
			}
		} catch (Exception e) {
		} finally {
			scanner.close();
			System.out.println(stringBuilder.toString());
		}
	}

	public static void add(int number, Map<Integer, TreeSet<Integer>> map) {
		Integer binaryOnes = getBinaryOnes(number);
		if (!map.containsKey(binaryOnes)) {
			TreeSet<Integer> set = new TreeSet<>();
			set.add(number);
			map.put(binaryOnes, set);
		} else
			map.get(binaryOnes).add(number);
	}

	public static int getBinaryOnes(int number) {
		short binaryOnes = 0;
		while (number > 0) {
			if (number % 2 == 1)
				binaryOnes++;
			number = number / 2;
		}
		return binaryOnes;
	}

	public static String getString(Map<Integer, TreeSet<Integer>> map) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Integer key : map.keySet()) {
			stringBuilder.append(map.get(key).first() + " ");
		}
		return stringBuilder.append('\n').toString();
	}

}
