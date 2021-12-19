package synthesizer; // TODO: Make sure to make this class a part of the synthesizer package

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {

        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        //       Make sure that your random numbers are different from each other.
        double lastR = -1.0; //the previous r in the deque
        for (int i = 0; i < buffer.fillCount(); i++) {
            buffer.dequeue(); //take first out of the list
            double r = Math.random() - 0.5; //find a random double number from -0.5 to 0.5
            while (r == lastR) { //if the number is the last one, you need a new number
                r = Math.random() - 0.5;
            }
            lastR = r; //set the last number as this one
            buffer.enqueue(r); //put the number in the list
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {

        double first = buffer.dequeue();
        double second = buffer.peek();
        double last = (first + second) * 0.5 * DECAY;
        buffer.enqueue(last);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.peek();
    }
}
