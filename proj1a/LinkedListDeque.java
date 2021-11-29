/** Project 1A Data Structures
 * This is Linked list based deque Implementation
 * Q 1 Linked List Deque:
 * I answer this question by checking gitbook chapter 2.3*/
public class LinkedListDeque <AnyType> {


    /** Basic list structure*/
    private class Node {
        /** instance variable for every IntNode*/
        public Node prev;
        public AnyType item;
        public Node next;
        /** constructor*/
        public Node(AnyType i, Node p, Node n) {
            this.prev = p;
            this.item = i;
            this.next = n;
        }
    }

    /** Instance variables*/
    private Node sentinel;
    private int size;

    /** Constructor */
    public LinkedListDeque(AnyType x) {
        /* why am I able to use null as the item for sentinel???*/
        sentinel = new Node ((AnyType) null, null, null);
        sentinel.next = new Node (x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    public LinkedListDeque() {
        /* why do I need to define separately? why can't I put sentinel in
        * p and n as parameter directly???*/
        sentinel = new Node ((AnyType) null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Basic rules
     * add a new item at the beginning of the list*/
    public void addFirst(AnyType x) {
        this.sentinel.next = new Node (x, sentinel, sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        size += 1;
    }

    /** add a new item at the end of the list*/
    public void addLast(AnyType x) {
        sentinel.prev = new Node (x, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /** remove an item at the beginning of the list*/
    public void removeFirst() {
        if (size > 0) {
            this.sentinel.next = this.sentinel.next.next;
            this.sentinel.next.prev = sentinel;
            size -= 1;
        }
    }

    /** remove an item at the end of the list*/
    public void removeLast() {
        if (size > 0) {
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
        }
    }

    /** get the item at index i */
    public AnyType get(int n) {
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
    public AnyType get_recur(int n) {
        return get_recur_helper(n, sentinel);
    }
    private AnyType get_recur_helper(int n, Node p){
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



    /** testing */
    public static void main(String[] args){
        LinkedListDeque<Integer> L = new LinkedListDeque<>(3);
        L.addFirst(4);
        L.addFirst(5);
        L.removeFirst();
        System.out.println(L.size());
        System.out.println(L.get(1));
        System.out.println(L.get_recur(1));

        L.addLast(2);
        System.out.println(L.size());
        System.out.println(L.get(2));
        System.out.println(L.get_recur(2));

        L.printDeque();

        LinkedListDeque<Integer> S = new LinkedListDeque<>();
        S.addFirst(4);
        S.addFirst(5);
        S.removeFirst();
        System.out.println(S.size());
        System.out.println(S.get(0));
        System.out.println(S.get_recur(0));
    }

}
