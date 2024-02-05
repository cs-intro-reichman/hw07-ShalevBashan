
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 0 || str.length() == 1) return "";
		String newStr = new String();
		for (int i = 1; i < str.length(); i++) {
			newStr += str.charAt(i);
		}
		return newStr;
	}

	public static int levenshtein(String word1, String word2) {
		String a = word1;
		String b = word2;
		if (a.length() == 0) return b.length();
		if (b.length() == 0) return a.length();
		if (a.charAt(0) == b.charAt(0)) return levenshtein(tail(a), tail(b));
		return 1 + Math.min(levenshtein(tail(a), b), Math.min(levenshtein(a, tail(b)), levenshtein(tail(a), tail(b))));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = threshold + 1;
		int at = -1;
		for (int i  = 0; i < dictionary.length; i++) {
			if (levenshtein(word, dictionary[i]) < min) {
				min = levenshtein(word, dictionary[i]);
				at = i;
			}
		}
		if (min > threshold) return word;
		return dictionary[at];
	}

}
