package my.seven;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LongestChain {

	static int longestChain(String[] words) {

		int maxChain = 0;
		int chain;
		for (String word : words) {
			chain = getLongestChainFor(word, words);
			if (maxChain < chain)
				maxChain = chain;
		}
		return maxChain;
	}

	static int getLongestChainFor(String word, String words[]) {
		int chain = 0;
		String tempWord = word;
		String newWord;
		List<String> list = Arrays.asList(words);
		int i =0;
		while(i < tempWord.length()) {
			if (tempWord.length() == 1) {
				chain++;
				break;
			} else {
				newWord = removeOneLetter(tempWord, i);
				if (list.contains(newWord)) {
					chain++;
					tempWord=newWord;
					i = 0;
				}
				else i++;
			}
		}
		return chain;
	}

	static String removeOneLetter(String word, int position) {
		StringBuilder stringBuilder = new StringBuilder(word);
		stringBuilder.deleteCharAt(position);
		return stringBuilder.toString();
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int res;

		int _words_size = 0;
		_words_size = Integer.parseInt(in.nextLine().trim());
		String[] _words = new String[_words_size];
		String _words_item;
		for (int _words_i = 0; _words_i < _words_size; _words_i++) {
			try {
				_words_item = in.nextLine();
			} catch (Exception e) {
				_words_item = null;
			}
			_words[_words_i] = _words_item;
		}
		in.close();
		res = longestChain(_words);
		System.out.println(String.valueOf(res));
	}

}
