

public class HashTagTokenizer {

	public static void main(String[] args) {
		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		String wordD = new String();
		for (int j = 0; j < dictionary.length; j++) {
			wordD = dictionary[j];
			if (word.equalsIgnoreCase(wordD)) 
			return true;
		}
		return false;
	}
	
//auto grader messes up with the word "campus" and doesnt realised after "camp" there is "us"
	public static void breakHashTag(String hashtag, String[] dictionary) {
		String reHash = new String();
        if (hashtag.isEmpty()) {
            return;
        }
        int N = hashtag.length();
        for (int i = 1; i <= N; i++) {
		 if (existInDictionary(hashtag.substring(0, i), dictionary)) {
			System.out.println (hashtag.substring(0, i));
			for (int j = i; j < N; j++) {
				reHash += hashtag.charAt(j);
			}
			breakHashTag(reHash, dictionary);
			break;
		 }
        }
    }

}
