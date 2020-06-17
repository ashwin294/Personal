package my.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddSymbols {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = in.readLine()) != null) {
			List<Integer> number = getList(s);
			List<String> result = new ArrayList<>();
			int previous = 0;
			for (Integer integer : number) {
				if (integer != 0 && previous != 0) {
					if (integer % 2 == 0 && previous % 2 == 0) {
						result.add("*");
					} else if (integer % 2 == 1 && previous % 2 == 1) {
						result.add("-");
					}
				}
				previous = integer;
				result.add(integer.toString());
			}
			for (String string : result) {
				System.out.print(string);
			}
		}

	}

	private static List<Integer> getList(String string) {

		List<Integer> list = new ArrayList();
		for (char string2 : string.trim().toCharArray()) {
			list.add(Integer.parseInt("" + string2));
		}
		return list;
	}
}
