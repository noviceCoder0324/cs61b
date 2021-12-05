/** this class is looking for the most palindromes in English for N*/
public class PalindromeCounter {

    public static void main(String[] args) {
        int minLength = 4;
        int maxIndex = 0;
        int prevMaxCount = 0;
        int maxCount = 0;

        for (int i = 0; i < 26; i ++) {

            In in = new In("../library-sp18/data/words.txt");
            Palindrome palindrome = new Palindrome();
            CharacterComparator cc = new OffByN(i);
            int wordLength = minLength;
            String longestWord = "";

            while (!in.isEmpty()) {
                String word = in.readString();

                if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                    maxCount += 1;
                    if (word.length() > wordLength) {
                        longestWord = word;
                        wordLength = word.length();
                    }
                }
            }
            System.out.println(longestWord);

            if (maxCount > prevMaxCount) {
                prevMaxCount = maxCount;
                maxIndex = i;
            }
            System.out.println(maxCount);
            maxCount = 0;
        }
        System.out.println(maxIndex);

    }
}
