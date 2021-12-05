/** This is Project 1B class. check if two Char are only miss by N.
 * @author Daoyu*/

public class OffByN implements CharacterComparator {
    /** instance variable.*/
    private final int n;

    /** constructor.
     * @param N the number we would like to each char miss.*/
    public OffByN(int N) {
        this.n = N;
    }


    /** compare if two char are equal.
     * @param x first char
     * @param y second char
     * @return true if they miss only by N.*/
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }
}
