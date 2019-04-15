import java.lang.reflect.Array;
import java.util.*;

/**
 * Doubly-linked list implementation of the {@code List} interface.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 * @param <E> the type of elements held in this collection
 */
public class MyLinkedList<E> implements List<E> {
    // Declare class attributes
    private int iSize = 0;
    private Node<E> nHead, nTail;
    private final static String UNSUPPORTED_OPERATION =
            "The chosen operation is not supported. Please try another one.";

    /**
     * Inner class used to store information about the elements of the current linked list implementation. Access
     * modifiers of variables are public because this class can only be accessed from the outer {@code MyLinkedList}
     * class.
     *
     * @param <E> the type of element for this node
     */
    private class Node<E> {
        // Declare class attributes
        public Node<E> nPrev, nNext;
        public E eElem;

        /**
         * Constructs a new node containing the given information.
         *
         * @param p_nPrev Represents the predecessor of the current element.
         * @param p_eElem Represents the current element of any type.
         * @param p_nNext Represents the successor of the current element.
         */
        public Node (Node<E> p_nPrev, E p_eElem, Node<E> p_nNext) {
            nPrev = p_nPrev;
            eElem = p_eElem;
            nNext = p_nNext;
        }
    }

    /**
     * Inner class which implements the {@code ListIterator<E>} interface. It is used to traverse through the linked
     * list.
     */
    private class MyListItr implements ListIterator<E> {
        // Declare class attributes
        private Node<E> nPrev, nNext;
        private int iNextIndex;

        /**
         * Constructs an {@code MyListItr} object to be able to traverse through the elements of the linked list.
         *
         * @param p_iIndex Represents the index where the iterator should start from.
         */
        public MyListItr (int p_iIndex) {
            // Set initial values for the class attributes
            nNext = (p_iIndex == iSize) ? null : getNode(p_iIndex);
            iNextIndex = p_iIndex;
        }

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the forward direction. (In other words,
         * returns {@code true} if {@link #next} would return an element rather
         * than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the forward direction
         */
        @Override
        public boolean hasNext() {
            return iNextIndex < iSize;
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         * This method may be called repeatedly to iterate through the list,
         * or intermixed with calls to {@link #previous} to go back and forth.
         * (Note that alternating calls to {@code next} and {@code previous}
         * will return the same element repeatedly.)
         *
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element
         */
        @Override
        public E next() {
            // If current element has no successor throw exception
            if (!hasNext())
                throw new NoSuchElementException();

            // Store predecessor of successor, get successor and increment next index by one
            nPrev = nNext;
            nNext = nNext.nNext;
            iNextIndex++;

            // Return the successor of the current element
            return nPrev.eElem;
        }

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the reverse direction.  (In other words,
         * returns {@code true} if {@link #previous} would return an element
         * rather than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the reverse direction
         */
        @Override
        public boolean hasPrevious() {
            return iNextIndex > 0;
        }

        /**
         * Returns the previous element in the list and moves the cursor
         * position backwards.  This method may be called repeatedly to
         * iterate through the list backwards, or intermixed with calls to
         * {@link #next} to go back and forth.  (Note that alternating calls
         * to {@code next} and {@code previous} will return the same
         * element repeatedly.)
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if the iteration has no previous
         *                                element
         */
        @Override
        public E previous() {
            // If current element has no predecessor throw exception
            if (!hasPrevious())
                throw new NoSuchElementException();

            // Get previous element of current element, decrement next index by one
            nPrev = nNext = (nNext == null) ? nTail : nNext.nPrev;
            iNextIndex--;

            // Return the predecessor of the current element
            return nPrev.eElem;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #next}. (Returns list size if the list
         * iterator is at the end of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code next}, or list size if the list
         * iterator is at the end of the list
         */
        @Override
        public int nextIndex() {
            return iNextIndex;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #previous}. (Returns -1 if the list
         * iterator is at the beginning of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code previous}, or -1 if the list
         * iterator is at the beginning of the list
         */
        @Override
        public int previousIndex() {
            return iNextIndex - 1;
        }

        /**
         * Removes from the list the last element that was returned by {@link
         * #next} or {@link #previous} (optional operation).  This call can
         * only be made once per call to {@code next} or {@code previous}.
         * It can be made only if {@link #add} has not been
         * called after the last call to {@code next} or {@code previous}.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this list iterator
         * @throws IllegalStateException         if neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to
         *                                       {@code next} or {@code previous}
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
        }

        /**
         * Replaces the last element returned by {@link #next} or
         * {@link #previous} with the specified element (optional operation).
         * This call can be made only if neither {@link #remove} nor {@link
         * #add} have been called after the last call to {@code next} or
         * {@code previous}.
         *
         * @param e the element with which to replace the last element returned by
         *          {@code next} or {@code previous}
         * @throws UnsupportedOperationException if the {@code set} operation
         *                                       is not supported by this list iterator
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this list
         * @throws IllegalArgumentException      if some aspect of the specified
         *                                       element prevents it from being added to this list
         * @throws IllegalStateException         if neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to
         *                                       {@code next} or {@code previous}
         */
        @Override
        public void set(E e) {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
        }

        /**
         * Inserts the specified element into the list (optional operation).
         * The element is inserted immediately before the element that
         * would be returned by {@link #next}, if any, and after the element
         * that would be returned by {@link #previous}, if any.  (If the
         * list contains no elements, the new element becomes the sole element
         * on the list.)  The new element is inserted before the implicit
         * cursor: a subsequent call to {@code next} would be unaffected, and a
         * subsequent call to {@code previous} would return the new element.
         * (This call increases by one the value that would be returned by a
         * call to {@code nextIndex} or {@code previousIndex}.)
         *
         * @param e the element to insert
         * @throws UnsupportedOperationException if the {@code add} method is
         *                                       not supported by this list iterator
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this list
         * @throws IllegalArgumentException      if some aspect of this element
         *                                       prevents it from being added to this list
         */
        @Override
        public void add(E e) {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
        }
    }

    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList() {}

    /**
     * Adds a new element to the end of the linked list.
     *
     * @param p_eElem Represents the element to be added to the list.
     */
    private void addLast (E p_eElem) {
        // Store current tail and the new element as unmodifiable nodes
        final Node<E> nOldTail = nTail;
        final Node<E> nNewNode = new Node<>(nOldTail, p_eElem, null);

        // Set new tail
        nTail = nNewNode;

        // If previous tail is null, set head of linked list
        if (nOldTail == null)
            nHead = nNewNode;
        // When previous tail is not null, set successor of it to the new element
        else
            nOldTail.nNext = nNewNode;

        // Increment the linked lists size by one
        iSize++;
    }

    /**
     * Adds a new element right before another element of the linked list.
     *
     * @param p_eElem    Represents the element to add to the list.
     * @param p_nNewNext Represents the element which will be the new elements successor.
     */
    private void addBefore (E p_eElem, Node<E> p_nNewNext) {
        // Store current predecessor and the new element as unmodifiable nodes
        final Node<E> nOldPrev = p_nNewNext.nPrev;
        final Node<E> nNewNode = new Node<>(nOldPrev, p_eElem, p_nNewNext);

        // Set predecessor of the new elements successor
        p_nNewNext.nPrev = nNewNode;

        // If the current predecessor is null, set head of linked list
        if (nOldPrev == null)
            nHead = nNewNode;
        // When current predecessor is not null, set successor of it to the new element
        else
            nOldPrev.nNext = nNewNode;

        // Increment the linked lists size by one
        iSize++;
    }

    /**
     * Quits all associations of the given element with other elements in the list by nulling the given element. Also
     * sets the new successor and predecessor of the associated elements.
     *
     * @param p_nElement Represents the element which shall quit its associations.
     * @return           Returns the element which quited all associations.
     */
    private E quitAssociations (Node<E> p_nElement) {
        // Store current elements data, its successor and predecessor in unmodifiable variables
        final E eElem = p_nElement.eElem;
        final Node<E> nNext = p_nElement.nNext;
        final Node<E> nPrev = p_nElement.nPrev;

        // If predecessor is null, set head to successor
        if (nPrev == null)
            nHead = nNext;
        // When predecessor is not null
        else {
            // Set new successor of predecessor of p_nElement
            nPrev.nNext = nNext;
            // Set predecessor of p_nElement null to delete it
            p_nElement.nPrev = null;
        }

        // If successor is null, set tail to predecessor
        if (nNext == null)
            nTail = nPrev;
        // When successor is not null
        else  {
            // Set new predecessor of successor of p_nElement
            nNext.nPrev = nPrev;
            // Set successor of p_nElement null to delete it
            p_nElement.nNext = null;
        }

        // Set data part of p_nElement null to delete it
        p_nElement.eElem = null;
        // Decrement the size of linked list
        iSize--;

        // Return the deleted element
        return eElem;
    }

    /**
     * Returns the number of elements in this list.  If this list contains
     * more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return iSize;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return iSize == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     * @see Arrays#asList(Object[])
     */
    @Override
    public Object[] toArray() {
        // Create new array and get head element for first iteration
        Object[] arrResult = new Object[iSize];
        Node<E> nElem = nHead;

        // Copy elements into array
        for (int i = 0; i < arrResult.length; i++) {
            arrResult[i] = nElem.eElem;
            nElem = nElem.nNext;
        }

        // Return array containing all elements from head to tail
        return arrResult;
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of {@code String}:
     *
     * <pre>{@code
     *     String[] y = x.toArray(new String[0]);
     * }</pre>
     * <p>
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        // Create new array of same type if size of linked list is greater than the arrays length
        if (a.length < iSize)
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), iSize);

        // Store reference to a in arrResult and get head element for first iteration
        Object[] arrResult = a;
        Node<E> nElem = nHead;

        // Copy elements into array
        for (int i = 0; i < arrResult.length; i++) {
            arrResult[i] = nElem.eElem;
            nElem = nElem.nNext;
        }

        // Set offset of array to null if its length is greater than the linked lists size
        if (a.length > iSize)
            a[iSize] = null;

        // Return array containing all elements from head to tail
        return a;
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param e element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the {@code add} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this list
     */
    @Override
    public boolean remove(Object o) {
        // If given object is null
        if (o == null) {
            // Loop through the linked lists elements from head to tail
            for (Node<E> nElem = nHead; nElem != null; nElem = nElem.nNext) {
                // If the null element was found
                if (nElem.eElem == null) {
                    // Remove the element by quitting all associations
                    quitAssociations(nElem);
                    // Return true as the element was removed
                    return true;
                }
            }
        }
        // When given object is not null
        else{
            // Loop through the linked lists elements from head to tail
            for (Node<E> nElem = nHead; nElem != null; nElem = nElem.nNext) {
                // If given object equals current element of linked list
                if (o.equals(nElem.eElem)) {
                    // Remove the element by  quitting all associations
                    quitAssociations(nElem);
                    // Return true as the element was removed
                    return true;
                }
            }
        }

        // Returns only false when the given object wasn't found
        return false;
    }

    /**
     * Returns {@code true} if this list contains all of the elements of the
     * specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return {@code true} if this list contains all of the elements of the
     * specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this list does not permit null
     *                              elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        // Loop through each object of the given collection
        for (Object o : c) {
            // If given collection doesn't contain the object return false
            if (!contains(o))
                return false;
        }

        // Returns only true when all objects of the collection are part of the linked list
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator (optional operation).  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @see #add(Object)
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code removeAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code retainAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *                                       is not supported by this list
     */
    @Override
    public void clear() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public E get(int index) {
        // Checks if given index is index of an existing element
        checkElementIndex(index);
        // Get node at given index and return its content
        return getNode(index).eElem;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code set} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    @Override
    public E set(int index, E element) {
        // Check if the given index equals an index of an existing element
        checkElementIndex(index);

        // Get the node which should be updated
        Node<E> nNodeToUpdate = getNode(index);

        // Store the old content of the node
        E eOldElem = nNodeToUpdate.eElem;

        // Set the new content of the node
        nNodeToUpdate.eElem = element;

        // Return the old content of the node
        return eOldElem;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException if the {@code add} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
    @Override
    public void add(int index, E element) {
        // Check if index is in bounds otherwise throw exception
        checkPositionIndex(index);

        // Check if the given index equals the linked lists size, if so add new element to end of linked list
        if (index == iSize)
            addLast(element);
        // When the index is not equal to the size, add the new element right before its new successor
        else
            addBefore(element, getNode(index));
    }

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    @Override
    public E remove(int index) {
        // Check if the given index equals an index of an existing element
        checkElementIndex(index);
        // Get the node with the given index and remove it by quitting all associations
        return quitAssociations(getNode(index));
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int indexOf(Object o) {
        // Init counter variable for loops and return value
        int iCounter = 0;

        // Branch to find first null reference
        if (o == null) {
            // Loop through all elements of the linked list starting with the head
            for (Node<E> nElem = nHead; nElem != null; nElem = nElem.nNext) {
                // When the null reference is found return the counter value
                if (nElem.eElem == null)
                    return iCounter;

                // Increment counter by one if null wasn't found
                iCounter++;
            }
        }
        // Branch to find first occurrence of given object
        else {
            // Loop through all elements of the linked list starting with the head
            for (Node<E> nElem = nHead; nElem != null; nElem = nElem.nNext) {
                // When the given object is found return the counter value
                if (o.equals(nElem.eElem))
                    return iCounter;

                // Increment counter by one if the given object wasn't found
                iCounter++;
            }
        }

        // Return minus one if the given object wasn't found at all
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int lastIndexOf(Object o) {
        // Init counter for loops and return value based on the linked lists size
        int iCounter = iSize;

        // Branch to find last null reference
        if (o == null) {
            // Loop through all elements of the linked list from tail to head
            for (Node<E> nElem = nTail; nElem != null; nElem = nElem.nPrev) {
                // Decrement counter by one each time
                iCounter--;

                // When the null reference is found return the counter value
                if (nElem.eElem == null)
                    return iCounter;
            }
        }
        // Branch to find last occurrence of given object
        else {
            // Loop through all elements of the linked list from tail to head
            for (Node<E> nElem = nTail; nElem != null; nElem = nElem.nPrev) {
                // Decrement counter by one each time
                iCounter--;

                // When the given object is found return the counter value
                if (o.equals(nElem.eElem))
                    return iCounter;
            }
        }

        // Return minus one if the given object wasn't found at all
        return -1;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     * sequence)
     */
    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        // Check if index is in bounds otherwise throw exception
        checkPositionIndex(index);

        // Return the iterator
        return new MyListItr(index);
    }

    /**
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for {@code indexOf} and
     * {@code lastIndexOf}, and all of the algorithms in the
     * {@code Collections} class can be applied to a subList.<p>
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *                                   ({@code fromIndex < 0 || toIndex > size ||
     *                                   fromIndex > toIndex})
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
    }

    /**
     * Returns the node at the specified index.
     *
     * @param p_iIndex Represents the index of the element to search for.
     * @return         Returns the node at the specified index.
     */
    private Node<E> getNode(int p_iIndex) {
        /*
         * Check whether the element is part of the first half of the linked list
         * iSize >> 1 (right bit shifting) equals iSize / 2 with Integer values
         * Example:
         * iSize = 5; iSize >> 1 = 2 | iSize = 6; iSize >> 1 = 3
         */
        if (p_iIndex < (iSize >> 1)) {
            // Get first node and store it
            Node<E> nElem = nHead;

            // Loop through the next nodes until the index is reached (forward)
            for (int i = 0; i < p_iIndex; i++)
                nElem = nElem.nNext;

            // Return the node at the current index
            return nElem;
        }
        // When the index is located in the second half of the linked list
        else {
            // Get last node and store it
            Node<E> nElem = nTail;

            // Loop through the previous nodes until the index is reached (backwards)
            for (int i = iSize - 1; i > p_iIndex; i--)
                nElem = nElem.nPrev;

            // Return the node at the current index
            return nElem;
        }
    }

    /**
     * Checks if the given index is in bounds of the linked lists size.
     * Used for add operations and the iterator.
     *
     * @param p_iIndex                   Represents the index to check.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    private void checkPositionIndex(int p_iIndex) {
        if (!(p_iIndex >= 0 && p_iIndex <= iSize))
            throw new IndexOutOfBoundsException(p_iIndex);
    }

    /**
     * Checks if the given index is a real element index.
     *
     * @param p_iIndex                   Represents the index to check.
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    private void checkElementIndex (int p_iIndex) {
        if (!(p_iIndex >= 0 && p_iIndex < iSize))
            throw new IndexOutOfBoundsException(p_iIndex);
    }
}
