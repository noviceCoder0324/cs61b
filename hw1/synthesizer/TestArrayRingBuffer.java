package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        Integer expected = 2;
        Integer actual = arb.dequeue();
        Integer expectedPeek = 3;
        Integer actualPeek = arb.peek();
        assertEquals(expected, actual);
        assertEquals(expectedPeek, actualPeek);
    }

    @Test
    public void testOverFlow() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(5);
        arb.enqueue(6);
        assertEquals((Integer)4, arb.peek());
        assertEquals((Integer)4, arb.dequeue());
        assertEquals(2, arb.fillCount);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
