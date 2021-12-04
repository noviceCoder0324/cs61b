/** Lab 3 A Debugging Mystery */

import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    @Test
    public void testIsSameNumber(){
        assertFalse(Flik.isSameNumber(1,3));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
