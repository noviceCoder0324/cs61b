/** This is Project 1B class. check if two Char are only miss by one.
 * @author Daoyu*/

public class OffByOne implements CharacterComparator {

    /** compare if two char are equal.
     * @param x first char
     * @param y second char
     * @return true if they miss only by one.*/
    @Override
    public boolean equalChars(char x, char y) {
        return  Math.abs(x - y) == 1;
    }
}
