
/** implement the interface as the super for A and D list.
 * @author Daoyu Liu*/
public interface Deque<T> {
    /** adds an item of type T to the front of the deque.
     * @param item the item needs to be added.*/
    void addFirst(T item);

    /** adds an item of type T to the back of the deque.
     * @param item the item needs to be added.*/
    void addLast(T item);

    /** Returns true if deque is empty, false otherwise.
     * @return true if deque is empty, false otherwise.*/
    boolean isEmpty();

    /** Return the number of items in the deque.
     * @return the size of the deque.*/
    int size();

    /** Prints the items in the deque from first to last,
     * separated by a space. */
    void printDeque();

    /** Removes and returns the item at the front of the deque. If
     * no such item exists, returns null.
     * @return the first item needs to be removed.*/
    T removeFirst();

    /** Removes and returns the item at the back of the deque.
     * @return the last item needs to be removed.*/
    T removeLast();

    /** Gets the item at the given index.
     * @param index the index we would like to get.
     * @return the item.*/
    T get(int index);
}
