import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByN {


    static CharacterComparator offByFive = new OffByN(5);

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByFive.equalChars('a', 'f'));
        assertTrue(offByFive.equalChars('f', 'a'));
        assertFalse(offByFive.equalChars('f', 'h'));
    }
}