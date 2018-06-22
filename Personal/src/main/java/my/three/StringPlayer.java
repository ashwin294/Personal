package my.three;

import java.util.ArrayList;


class MyArrayList<E> extends ArrayList<E> {
	@Override
	public E get(int index) {
		if (index + 1 > size() || index < 0)
			return super.get(index % size());
		else
			return super.get(index);
	}
}

public class StringPlayer {

	public static void main(String[] args) {
		 String s1 =
		 "MexIcobMexIcobFILbMexIcobMexIcobjMexIcobvMexIcobMexIcobMexIcobgMexIcobdMexIcobZVMexIcobMexIcobaMexIcobcoMexI";
		 String a1 = "MexIco";
//		String s1 = "AbrAcadAbRa";
//		String a1 = "cAda";
		int s = s1.length();
		int a = a1.length();
		StringPlayer player = new StringPlayer();
		// System.out.println("\n\n\n\n>>>>> "
		// +player.findStringMatch(s1.toLowerCase(), a1.toLowerCase(), 11, 4));
		System.out.println("\n\n\n\n>>>>> " + appearanceCount(a, s, a1, s1));

	}

	public static int appearanceCount1(int input1, int input2, String input3, String input4) {
		input3 = input3.toLowerCase();
		input4 = input4.toLowerCase();
		String aa = input3 + input3;
		int totalMatches = 0;
		for (int i = 0; i < input2 - input1; i++) {
			if (aa.contains(input4.substring(i, i + input1)))
				totalMatches++;
		}

		return totalMatches;
	}

	public static int appearanceCount(int input1, int input2, String input3, String input4) {
		int totalMatches = 0;
		ArrayList<String> input3List = getArrayList(input3);
		for (int i = 0; i <= input2 - input1; i++) {
			ArrayList<String> sublist = getArrayList(input4.substring(i, i + input1));
			if (sublist.containsAll(input3List)){
				totalMatches++;
			}
		}
		return totalMatches;
	}

	//
	//
	// private int findStringMatch_(String s1, String a1, int s, int a) {
	//
	// MyArrayList<String> mainString = getList(s1);
	// MyArrayList<String> subString = getList(a1);
	// int totalMatches = 0;
	// for (int i = 0; i < s; i++) {
	// int matches = 0;
	// String string = mainString.get(i);
	// if (subString.contains(string)) {
	// int index = subString.indexOf(string);
	// matches++;
	// int j = i;
	// System.out.print("("+string+")");
	// while (j < s && subString.get(++index).equals(mainString.get(++j))) {
	// matches++;
	// System.out.print(mainString.get(j) + index + ",");
	// if (matches == a) {
	// totalMatches++;
	// System.out.print("!\n");
	// break;
	// }
	// }
	// System.out.print("<\n");
	// } else {
	// System.out.print(string + "<<\n");
	// }
	// }
	// return totalMatches;
	//
	// }
	//
	//
	//
	//
	//
	private  MyArrayList<String> getList(String s1) {
		MyArrayList<String> mainString = new MyArrayList<String>();
		for (char c : s1.toCharArray()) {
			mainString.add("" + c);
		}
		return mainString;
	}
	
	private static ArrayList<String> getArrayList(String s1) {
		ArrayList<String> mainString = new ArrayList<String>();
		for (char c : s1.toCharArray()) {
			mainString.add("" + c);
		}
		return mainString;
	}

	private int findStringMatch(String s1, String a1, int s, int a) {
		String aa = a1 + a1;
		int totalMatches = 0;
		for (int i = 0; i < s - a; i++) {
			if (aa.contains(s1.substring(i, i + a)))
				totalMatches++;
		}

		return totalMatches;
	}
}
