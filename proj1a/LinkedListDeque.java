import junit.framework.Test;

import javax.swing.plaf.TabbedPaneUI;

/** Project 1A Data Structures
 * This is Linked list based deque Implementation
 * Q 1 Linked List Deque:
 * I answer this question by checking gitbook chapter 2.3*/
public class LinkedListDeque <T> {


    /** Basic list structure*/
    private class Node {
        /** instance variable for every IntNode*/
        public Node prev;
        public T item;
        public Node next;
        /** constructor*/
        public Node(T i, Node p, Node n) {
            this.prev = p;
            this.item = i;
            this.next = n;
        }
    }

    /** Instance variables*/
    private Node sentinel;
    private int size;

    /** Constructor */
    public LinkedListDeque(T x) {
        /* why am I able to use null as the item for sentinel???*/
        sentinel = new Node ((T) null, null, null);
        sentinel.next = new Node (x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    public LinkedListDeque() {
        /* why do I need to define separately? why can't I put sentinel in
        * p and n as parameter directly???*/
        sentinel = new Node ((T) null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Basic rules
     * add a new item at the beginning of the list*/
    public void addFirst(T x) {
        this.sentinel.next = new Node (x, sentinel, sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        size += 1;
    }

    /** add a new item at the end of the list*/
    public void addLast(T x) {
        sentinel.prev = new Node (x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /** remove an item at the beginning of the list*/
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

    /** remove an item at the end of the list*/
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

    /** get the item at index i */
    public T get(int n) {
        if (n < size) {
            Node p = sentinel;
            int i = 0;
            while ( i <= n){
                p = p.next;
                i += 1;
            }
            return p.item;
        }
        return sentinel.item;
    }

    /** get the item at index i recursively*/
    public T getRecursive(int n) {
        return get_recur_helper(n, sentinel);
    }
    private T get_recur_helper(int n, Node p){
        if (n == 0) {
            return p.next.item;
        }else{
            return get_recur_helper(n-1, p.next);
        }
    }

    /** return the size of the list*/
    public int size() {
        return this.size;
    }

    /** return if a list is empty */
    public boolean isEmpty() {
        if (size <= 0) {
            return true;
        }
        return false;
    }

    /** print the list from first to last, separated by a space */
    public void printDeque(){
        if (size > 0) {
            String result = "";
            Node p = sentinel;
            int i = 0;
            while (i < size){
                result = result + p.next.item + " ";
                p = p.next;
                i += 1;
            }
            System.out.println(result);
        }
    }


}
