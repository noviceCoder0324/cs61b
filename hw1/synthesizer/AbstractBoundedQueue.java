package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    /**These are fields.*/
    protected int capacity;
    protected int fillCount;

    // these are coming from BoundedQueue
    public int capacity() {
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
    //public boolean isEmpty()
    //public boolean isFull()

    /**additional abstract methods*/
    public abstract T peek();

    public abstract T dequeue();

    public abstract void enqueue(T x);
}

