import java.util.*;

/**
 * This Class implements a generic linked list with all relevant methods from the <code>List</code> and
 * <code>Deque</code> Interfaces.
 * @param <T> Insert any type of Object
 */
public class MyLinkedList<T> implements List<T>, Deque<T> {

    /**
     * This inner Class generates Nodes, which are then used in a doubly linked list. Attributes can be public, since
     * its a private class.
     */
    private class Node {
        // Data contained by a Node
        public T data;
        // Prevoius Node
        public Node prev;
        // Next Node
        public Node next;
        // Constructor of Node Class
        public Node(T prm_data, Node prm_prev, Node prm_next) {
            this.data = prm_data;
            this.prev = prm_prev;
            this.next = prm_next;
        }
    }

    // Head element, first element of list
    private Node head;
    // Tail element, last element of list
    private Node tail;
    // Size of the list
    private int size;

    /**
     * Constructor of the <code>MyLinkedList</code> Class.
     */
    public MyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * This methods gives the size of the list.
     * @return Size of list as integer.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * This method checks if the list is empty.
     * @return True if empty list, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    /**
     * Adds an element at first position.
     * @param t Element that is being added
     */
    @Override
    public void addFirst(T t) {
        // Create new Node for Element
        Node temp = new Node(t, null, head);
        // Create link from new element to head
        if(head != null) {
            head.prev = temp;
        }
        // The element is now the new head
        head = temp;
        if(tail == null) {
            tail = temp;
        }
        // increase list size
        size++;
    }

    /**
     * Adds an element at last position.
     * @param t Element that is being added
     */
    @Override
    public void addLast(T t) {
        // Create new Node for Element
        Node temp = new Node(t, tail, null);
        // Create link from tail to next element
        if(tail != null) {
            tail.next = temp;
        }
        // The element is now the new tail
        tail = temp;
        if(head == null) {
            head = temp;
        }
        // increase list size
        size++;
    }

    @Override
    public boolean offerFirst(T t) {
        return false;
    }

    @Override
    public boolean offerLast(T t) {
        return false;
    }

    /**
     * This method is used to remove the first element of the list and give a reference to it.
     * @return Returns first element of the list.
     */
    @Override
    public T removeFirst() {
        // in case there is no element
        if (size == 0) throw new NoSuchElementException();
        // Get the first element
        Node temp = head;
        // Set head to second element
        head = head.next;
        // Delete reference to previous element
        head.prev = null;
        // decrease size of list
        size--;
        return temp.data;
    }

    /**
     * This method is used to remove the last element of the list and give a reference to it.
     * @return Returns last element of the list.
     */
    @Override
    public T removeLast() {
        // in case there is no element
        if (size == 0) throw new NoSuchElementException();
        // Get last element
        Node temp = tail;
        // Set Tail to second to last element
        tail = tail.prev;
        // Delete reference to next element
        tail.next = null;
        // decrease size
        size--;
        return temp.data;
    }

    @Override
    public T pollFirst() {
        return null;
    }

    @Override
    public T pollLast() {
        return null;
    }

    @Override
    public T getFirst() {

        return null;
    }

    @Override
    public T getLast() {

        return null;
    }

    /**
     * Gives a reference to the first element of the list.
     * @return First element
     */
    @Override
    public T peekFirst() {
        return this.head.data;
    }

    /**
     * Gives a reference to the last element of the list.
     * @return Last element
     */
    @Override
    public T peekLast() {
        return this.tail.data;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(T t) {
        // optional
        return false;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // optional
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public void push(T t) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        // optional
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // optional
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        // optional
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
        // optional
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
