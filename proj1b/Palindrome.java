/** Class Palindrome
 * @author Daoyu Liu
 * Project 1B CS61b*/
public class Palindrome {

    /** return a Deque where the characters appear.
     * in the same order as the String.
     * @param word the string we would like to put into deque.
     * @return the Deque list we have.*/
    public Deque<Character> wordToDeque(String word) {
        if (word.length() == 0) {
            return null;
        }
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            result.addLast(Character.valueOf(ch));
        }
        return result;
    }

    /** check if a String is Palindrome iteratively.
     * @param word the string we would like to check.
     * @return true if it is palindrome*/
    private boolean isPalindromeIter(String word) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> lst = wordToDeque(word);
        for (int i = 0; i < lst.size() / 2; i++) {
            if (lst.get(i) != lst.get(lst.size() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /** check if a String is Palindrome recursively.
     * @param word the string we would like to check.
     * @return true if it is palindrome.*/
    public boolean isPalindrome(String word) {
        Deque<Character> lst = wordToDeque(word);
        return isPalindrome(lst);
    }
    /** the helper function for isPalindrome.
     * @param lst the Deque list has all chars
     * @return true if Characters are palindrome*/
    private boolean isPalindrome(Deque<Character> lst) {
        if (lst.size() == 1 || lst.size() == 0) {
            return true;
        } else {
            if (lst.removeFirst() != lst.removeLast()) {
                return false;
            }
            return isPalindrome(lst);
        }
    }

    /** This will return true. If the word is palindrome
     * according to the character comparison test provided
     * by theCharacterComparator passed in as argument.
     * @param word the word we would like to check
     * @param cc the character comparator we like to use.*/
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> lst = wordToDeque(word);
        return isPalindrome(lst, cc);
    }
    /** This is the helper function for the previous one.
     * @param lst the deque list we are using.
     * @param cc the character comparator we use.*/
    private boolean isPalindrome(Deque<Character> lst, CharacterComparator cc) {
        if (lst.size() == 1 || lst.size() == 0) {
            return true;
        } else {
            if (!cc.equalChars(lst.removeFirst(), lst.removeLast())) {
                return false;
            }
            return isPalindrome(lst, cc);
        }
    }

}
