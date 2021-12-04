
/** Project 1A Data Structures.
 * This is Linked list based deque Implementation
 * Q 1 Linked List Deque:
 * I answer this question by checking gitbook chapter 2.3
 * @author PB */
public class LinkedListDeque<T> {


    /** Basic list structure. */
    private class Node {
        /** instance variable for every IntNode. */
        private Node prev;
        /** the item itself. */
        private T item;
        /** next node in the list. */
        private Node next;
        /** constructor.
         * @param i the item.
         * @param p the previous node.
         * @param n the next node.*/
        private Node(T i, Node p, Node n) {
            this.prev = p;
            this.item = i;
            this.next = n;
        }
    }

    /** Instance variables. */
    private Node sentinel;
    /** the size of the list. */
    private int size;

    /** Constructor. */
    public LinkedListDeque() {
        /* why do I need to define separately? why can't I put sentinel in
        * p and n as parameter directly???*/
        sentinel = new Node((T) null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Basic rules.
     * add a new item at the beginning of the list
     * @param x the item.*/
    public void addFirst(T x) {
        this.sentinel.next = new Node(x, sentinel, sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        size += 1;
    }

    /** add a new item at the end of the list.
     * @param x the item to be added. */
    public void addLast(T x) {
        sentinel.prev = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /** remove an item at the beginning of the list.
     * @return  the item just be removed.*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = this.sentinel.next.item;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = sentinel;
        size -= 1;
        return result;
    }

    /** remove an item at the end of the list.
     * @return  the item just be removed. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = this.sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return result;
    }

    /** get the item at index i.
     * @param n the index of the item
     * @return the item at n position.*/
    public T get(int n) {
        if (n < size) {
            Node p = sentinel;
            int i = 0;
            while (i <= n) {
                p = p.next;
                i += 1;
            }
            return p.item;
        }
        return sentinel.item;
    }

    /** get the item at index i recursively.
     *  @param n the index of the item.
     *  @return the item.*/
    public T getRecursive(int n) {
        return getRecursive(n, sentinel);
    }
    /** helper function.
     * @param n the index of the item.
     * @param p the pointer.
     * @return the item.*/
    private T getRecursive(int n, Node p) {
        if (n == 0) {
            return p.next.item;
        } else {
            return getRecursive(n - 1, p.next);
        }
    }

    /** return the size of the list.
     * @return the size of the list. */
    public int size() {
        return this.size;
    }

    /** return if a list is empty.
     * @return true if the list is empty.*/
    public boolean isEmpty() {
        if (size <= 0) {
            return true;
        }
        return false;
    }

    /** print the list from first to last, separated by a space. */
    public void printDeque() {
        if (size > 0) {
            String result = "";
            Node p = sentinel;
            int i = 0;
            while (i < size) {
                result = result + p.next.item + " ";
                p = p.next;
                i += 1;
            }
            System.out.println(result);
        }
    }


}
