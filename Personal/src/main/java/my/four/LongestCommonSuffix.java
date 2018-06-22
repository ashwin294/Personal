package my.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LongestCommonSuffix {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			String[] words = s.trim().split(",");
			int i, j;
			char[] big;
			char[] small;
			if (words[0].length() > words[0].length()) {
				big = words[0].trim().toCharArray();
				small = words[1].trim().toCharArray();
			} else {
				big = words[1].trim().toCharArray();
				small = words[0].trim().toCharArray();
			}
			List<String> matched = new ArrayList<>();
			for (i = big.length - 1, j = small.length - 1; i >= 0 && j >= 0; i--, j--) {
				if (("" + big[i]).equals("" + small[j]))
					matched.add("" + big[i]);
				else
					break;
			}
			if (matched.isEmpty()) {
				System.out.println("NULL");
			} else {
				Collections.reverse(matched);
				for (String string : matched) {
					System.out.print(string);
				}
			}
		}

	}
}