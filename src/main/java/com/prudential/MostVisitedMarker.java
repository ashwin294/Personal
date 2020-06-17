package com.prudential;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Result {

	/*
	 * Complete the 'getMostVisited' function below.
	 *
	 * The function is expected to return an INTEGER.
	 * The function accepts following parameters:
	 *  1. INTEGER n
	 *  2. INTEGER_ARRAY sprints
	 */

	public static int getMostVisited(int n, List<Integer> sprints) {
		// Write your code here

		int[] markers = new int[n + 1];
		markers[0] = Integer.MAX_VALUE;
		int i, j;
		int offset;
		int mostVisitedMarker = 0;
		for (i = 0, j = 1; j < sprints.size(); i++, j++) {
			offset = (sprints.get(j) - sprints.get(i)) / Math.abs((int) (sprints.get(i) - sprints.get(j)));
			System.out.println();
			int k;
			for (k = 0; k <= Math.abs((int) (sprints.get(i) - sprints.get(j))); k++) {
				if (++markers[sprints.get(i) + (offset * k)] > mostVisitedMarker)
					mostVisitedMarker = markers[sprints.get(i) + (offset * k)];
			}
		}

		for (int k = 1; k < markers.length; k++) {
			if (markers[k] == mostVisitedMarker)
				return k;
		}
		return 0;
	}

}

/**
 * Problem statement is in the resources dir with the same name as the class.
 */
public class MostVisitedMarker {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int n = Integer.parseInt(bufferedReader.readLine().trim());

		int sprintsCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> sprints = IntStream.range(0, sprintsCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		})
				.map(String::trim)
				.map(Integer::parseInt)
				.collect(toList());

		int result = Result.getMostVisited(n, sprints);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}

//    10
//
//			4
//
//			1
//
//			5
//
//			10
//
//			3

//    9
//
//			4
//
//			9
//
//			7
//
//			3
//
//			1
//Answer: 3

