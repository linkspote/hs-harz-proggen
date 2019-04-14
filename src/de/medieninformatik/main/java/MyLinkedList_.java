import java.util.*;

/**
 * This Class implements a generic linked list with all relevant methods from the <code>List</code> and
 * <code>Deque</code> Interfaces.
 * @param <T> Insert any type of Object
 */
public class MyLinkedList_<T> implements List<T> {

    /**
     * This inner Class generates Nodes, which are then used in a doubly linked list. Attributes can be public, since
     * its a private class.
     */
    private class Node {
        // Data contained by a Node
        public T data;
        // Previous Node
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
    public MyLinkedList_() {
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

    /**
     * This methods checks if an element is contained in the list.
     * @param o Element which is tested
     * @return Ture if element is in list, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        // Check if head contains the element
        Node temp = head;
        boolean res = (head.data.equals(o));
        // check if later element contain the element
        do {
            temp = temp.next;
            if(temp.data.equals(o)) {
                res = true;
                break;
            }
        } while (temp != tail);
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    /**
     * Copies all elements of the list into an array of type Object.
     * @return Array of elements of type object.
     */
    @Override
    public Object[] toArray() {
        // Get array and first element
        Object[] arrTemp = new Object[size];
        Node temp = head;
        // Copy elements into array
        for (int i = 0; i < arrTemp.length; i++) {
            arrTemp[i] = temp.data;
            temp = temp.next;
        }
        return arrTemp;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // ???
        return null;
    }

    @Override
    public boolean add(T t) {
        // optional
        return false;
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

    /**
     * This methods gives a reference to an element at a specified index.
     * @param index Index of the element
     * @return Element at specific index
     */
    @Override
    public T get(int index) {
        if(index >= size || index < 0) throw new IndexOutOfBoundsException();
        // Start looking for the element from head Node
        Node temp = head;
        // Go through all elements to the element at given index
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * This method replace an element at specified position with a new element and gives a reference to the old element.
     * @param index Index of the element
     * @param element New element to be inserted
     * @return Returns element previously at given index
     */
    @Override
    public T set(int index, T element) {
        if(index >= size || index < 0) throw new IndexOutOfBoundsException();
        // Start looking for the element from head Node
        Node temp = head;
        // Go through all elements to the element at given index
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        // Temporarily store previous element
        T prevData = temp.data;
        // Set element at specific positioon
        temp.data = element;
        return prevData;
    }

    @Override
    public void add(int index, T element) {
        // optional
    }

    @Override
    public T remove(int index) {
        return null;
    }

    /**
     * This method gives the first index at which the specified element appears in the list.
     * @param o Element to search for
     * @return Index of the given element or -1, if the element is not in the list
     */
    @Override
    public int indexOf(Object o) {
        int index = -1;
        // Check if head contains the element
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if(temp.data.equals(o)) {
                index = i;
                break;
            } else temp = temp.next;
        }
        return index;
    }

    /**
     * This method gives the last index at which the specified element appears in the list.
     * @param o Element to search for
     * @return Index of the given element or -1, if the element is not in the list
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        // Check if head contains the element
        Node temp = tail;
        for (int i = size-1; i >= 0; i--) {
            if(temp.data.equals(o)) {
                index = i;
                break;
            } else temp = temp.prev;
        }
        return index;
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
