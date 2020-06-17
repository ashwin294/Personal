package com.prudential;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Problem statement is in the resources dir with the same name as the class.
 */
public class MatrixPathTraversal {

	public MatrixPathTraversal() {
	}


	/*
	 * Complete the 'numPaths' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts 2D_INTEGER_ARRAY warehouse as parameter.
	 */

	public static int numPaths(List<List<Integer>> warehouse) {
		// Write your code here
		if (!isOne(warehouse.get(0).get(0)))
			return 0;

		int rowMax = warehouse.size();
		int colMax = warehouse.get(0).size();

		return getPaths(warehouse, 0, 0, rowMax - 1, colMax - 1) % ((int) Math.pow(10, 9) + 7);
	}

	public static int getPaths(List<List<Integer>> warehouse, int i, int j, int rowMax, int colMax) {

		if (i == rowMax && j == colMax) {
			return 1;
		}

		int path1 = 0;
		int path2 = 0;

		if (i < rowMax && isOne(warehouse.get(i + 1).get(j)))
			path1 = getPaths(warehouse, i + 1, j, rowMax, colMax);
		if (j < colMax && isOne(warehouse.get(i).get(j + 1)))
			path2 = getPaths(warehouse, i, j + 1, rowMax, colMax);

		return path1 + path2;
	}

	public static boolean isOne(Integer i) {
		return i == 1;
	}

}

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int warehouseRows = Integer.parseInt(bufferedReader.readLine().trim());
		int warehouseColumns = Integer.parseInt(bufferedReader.readLine().trim());

		List<List<Integer>> warehouse = new ArrayList<>();

		IntStream.range(0, warehouseRows).forEach(i -> {
			try {
				warehouse.add(
						Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
								.map(Integer::parseInt)
								.collect(toList())
				);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		int result = MatrixPathTraversal
				.numPaths(warehouse);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
// Input test case
//    10
//
//			10
//
//			1 1 1 1 1 0 0 1 0 1
//
//			1 1 1 1 0 0 1 0 1 0
//
//			1 1 1 1 0 1 0 1 0 1
//
//			1 1 0 1 1 1 1 0 1 0
//
//			1 1 0 1 0 1 0 1 0 1
//
//			1 1 1 1 1 1 1 1 1 1
//
//			1 1 0 1 1 0 1 1 0 1
//
//			1 1 0 0 1 1 0 1 0 1
//
//			0 1 1 1 1 1 1 1 1 1
//
//			1 1 1 1 1 1 1 1 1 1