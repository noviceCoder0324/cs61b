/**Project 1A Data Structures.
 * This will use arrays as the core data structure.
 * @author Daoyu Liu */
public class ArrayDeque<T> implements Deque<T> {

    /** instance variables. */
    private T[] items;
    /** size of the list.*/
    private int size;
    /** first index of the list.*/
    private int firstIndex;
    /** last index of the list. */
    private int lastIndex;

    /** constant. */
    private static final int START_SIZE = 8;

    /** create an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[START_SIZE];
        firstIndex = 0;
        lastIndex = 0;
        size = 0;
    }

    /** addFirst method.
     * @param x the item needs to be added. */
    @Override
    public void addFirst(T x) {
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
            items[0] = x;
            size += 1;
            return;
        }
        if (size == items.length) {
            resizeUp();
        }
        if (firstIndex == 0) {
            firstIndex = items.length - 1;
        } else {
            firstIndex--;
        }
        items[firstIndex] = x;
        size += 1;
    }

    /** addLast method.
     * @param x the item needs to be added.*/
    @Override
    public void addLast(T x) {
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
            items[0] = x;
            size += 1;
            return;
        }
        if (size == items.length) {
            resizeUp();
        }
        if (lastIndex == items.length - 1) {
            lastIndex = 0;
        } else {
            lastIndex += 1;
        }
        items[lastIndex] = x;
        size += 1;
    }

    /** get something from the list. this is smart.
     * @param index the index of the item.
     * @return the item.*/
    @Override
    public T get(int index) {
        return items[(firstIndex + index) % items.length];
    }

    /** check if it is empty.
     * @return true if the list is empty.*/
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** return the size.
     * @return the size of the list.*/
    @Override
    public int size() {
        if (size <= 0) {
            return 0;
        }
        return size;
    }

    /** print out the outcome for the Deque. */
    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }
    }

    /** remove the first from the list.
     * @return the item.*/
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removeNode = items[firstIndex];
        items[firstIndex] = null;
        if (firstIndex == items.length - 1) {
            firstIndex = 0;
        } else {
            firstIndex += 1;
        }
        size -= 1;
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
        }
        if (size < items.length / 4) {
            resizeDown();
        }
        return removeNode;
    }

    /** remove the last from the list.
     * @return the item.*/
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removeNode = items[lastIndex];
        items[lastIndex] = null;
        if (lastIndex == 0) {
            lastIndex = items.length - 1;
        } else {
            lastIndex -= 1;
        }
        size -= 1;
        if (size == 0) {
            firstIndex = 0;
            lastIndex = 0;
        }
        if (size < items.length / 4) {
            resizeDown();
        }
        return removeNode;
    }

    /** resize the list and make it bigger. */
    private void resizeUp() {
        T[] resizeArray = (T[]) new Object[items.length * 2];
        int sizeOfFirstCopy = items.length - firstIndex;
        System.arraycopy(items, firstIndex, resizeArray, 0, sizeOfFirstCopy);
        System.arraycopy(items, 0, resizeArray, sizeOfFirstCopy, size - sizeOfFirstCopy);
        items = resizeArray;
        firstIndex = 0;
        lastIndex = size - 1;
    }

    /** resize down the list. */
    private void resizeDown() {
        T[] resizeArray = (T[]) new Object[items.length / 2];
        if (lastIndex < firstIndex) {
            int sizeOfFirstCopy = items.length - firstIndex;
            System.arraycopy(items, firstIndex, resizeArray, 0, sizeOfFirstCopy);
            System.arraycopy(items, 0, resizeArray, sizeOfFirstCopy, size - sizeOfFirstCopy);
        } else {
            System.arraycopy(items, firstIndex, resizeArray, 0, size);
        }
        items = resizeArray;
        firstIndex = 0;
        lastIndex = size - 1;
    }
}
