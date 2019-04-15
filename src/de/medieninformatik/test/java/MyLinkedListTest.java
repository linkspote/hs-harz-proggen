import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code MyLinkedListTest} class is used to test the functionality of the {@code MyLinkedList} class which
 * contains the methods that need to be tested.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
class MyLinkedListTest {

    /**
     * Test size() method of {@code MyLinkedList} class.
     */
    @Test
    void size() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Check if the expected result equals the return value of the size() method
        assertEquals(0, mllTest.size());

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected result equals the return value of the size() method
        assertEquals(3, mllTest.size());
    }

    /**
     * Test isEmpty() method of {@code MyLinkedList} class.
     */
    @Test
    void isEmpty() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Check if the expected result equals the return value of the isEmpty() method
        assertTrue(mllTest.isEmpty());

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected result equals the return value of the isEmpty() method
        assertFalse(mllTest.isEmpty());
    }

    /**
     * Test contains() method of {@code MyLinkedList} class.
     */
    @Test
    void contains() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Check if the expected result equals the return value of the contains() method
        assertFalse(mllTest.contains(2));

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected result equals the return value of the contains() method
        assertTrue(mllTest.contains(2));
    }

    /**
     * Test iterator() method of {@code MyLinkedList} class.
     */
    @Test
    void iterator() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Get iterator of MyLinkedList
        Iterator itrTest = mllTest.iterator();

        // Check if the expected result equals the return value of the iterator() method
        assertNotNull(itrTest);
    }

    /**
     * Test toArray() method of {@code MyLinkedList} class.
     */
    @Test
    void toArray() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Store array in variable
        Object[] arrTest = mllTest.toArray();

        // Perform tests for values and compare the size of both
        assertEquals(1, arrTest[0]);
        assertEquals(2, arrTest[1]);
        assertEquals(3, arrTest[2]);
        assertEquals(arrTest.length, mllTest.size());
    }

    /**
     * Test second toArray() method of {@code MyLinkedList} class.
     */
    @Test
    void toArray1() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Create new array of size 2 (not enough space for all value)
        Integer[] arrTest = new Integer[2];
        // Call toArray method which returns array of same type with more space if needed
        arrTest = mllTest.toArray(arrTest);

        // Perform tests for values and compare the size of both
        assertEquals(1, arrTest[0]);
        assertEquals(2, arrTest[1]);
        assertEquals(3, arrTest[2]);
        assertEquals(arrTest.length, mllTest.size());
    }

    /**
     * Test add() method of {@code MyLinkedList} class.
     */
    @Test
    void add() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Test if adding elements to the list works correct
        assertTrue(mllTest.add(1));
        assertTrue(mllTest.add(2));
        assertTrue(mllTest.add(3));

        // Check again by comparing the amount of added elements to the actual size of the list
        assertEquals(3, mllTest.size());
    }

    /**
     * Test remove() method of {@code MyLinkedList} class.
     */
    @Test
    void remove() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected results equal the return values of the called methods
        assertEquals(2, mllTest.remove(1));
        assertEquals(2, mllTest.size());
    }

    /**
     * Test containsAll() method of {@code MyLinkedList} class.
     */
    @Test
    void containsAll() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Create another MyLinkedList
        MyLinkedList<Integer> mllTest2 = new MyLinkedList<>();

        // Add some elements to the second test list
        mllTest2.add(1);
        mllTest2.add(2);
        mllTest2.add(3);

        // Check if the expected result equals the return value of the containsAll() method
        assertTrue(mllTest.containsAll(mllTest2));

        // Add one more to the second test list
        mllTest2.add(4);

        // Check if the expected result equals the return value of the containsAll() method
        assertFalse(mllTest.containsAll(mllTest2));
    }

    /**
     * Test addAll() method of {@code MyLinkedList} class.
     */
    @Test
    void addAll() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Create another MyLinkedList
        MyLinkedList<Integer> mllTest2 = new MyLinkedList<>();

        // Add some elements to the second test list
        mllTest2.add(1);
        mllTest2.add(2);
        mllTest2.add(3);

        // Check if the expected results equal the returned values of the addAll() method
        Exception oException = assertThrows(UnsupportedOperationException.class, () -> mllTest.addAll(mllTest2));
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());
    }

    /**
     * Test second addAll() method of {@code MyLinkedList} class.
     */
    @Test
    void addAll1() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Create another MyLinkedList
        MyLinkedList<Integer> mllTest2 = new MyLinkedList<>();

        // Add some elements to the second test list
        mllTest2.add(1);
        mllTest2.add(2);
        mllTest2.add(3);

        // Check if the expected results equal the returned values of the second addAll() method
        Exception oException = assertThrows(UnsupportedOperationException.class, () -> mllTest.addAll(1, mllTest2));
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());
    }

    /**
     * Test removeAll() method of {@code MyLinkedList} class.
     */
    @Test
    void removeAll() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Create another MyLinkedList
        MyLinkedList<Integer> mllTest2 = new MyLinkedList<>();

        // Add some elements to the second test list
        mllTest2.add(2);
        mllTest2.add(3);

        // Check if the expected results equal the returned values of the removeAll() method
        Exception oException = assertThrows(UnsupportedOperationException.class, () -> mllTest.removeAll(mllTest2));
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());
    }

    /**
     * Test retainAll() method of {@code MyLinkedList} class.
     */
    @Test
    void retainAll() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Create another MyLinkedList
        MyLinkedList<Integer> mllTest2 = new MyLinkedList<>();

        // Add some elements to the second test list
        mllTest2.add(1);
        mllTest2.add(2);

        // Check if the expected results equal the returned values of the retainAll() method
        Exception oException = assertThrows(UnsupportedOperationException.class, () -> mllTest.retainAll(mllTest2));
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());
    }

    /**
     * Test clear() method of {@code MyLinkedList} class.
     */
    @Test
    void clear() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected results equal the "returned values" of the clear() method
        Exception oException = assertThrows(UnsupportedOperationException.class, mllTest::clear);
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());
    }

    /**
     * Test get() method of {@code MyLinkedList} class.
     */
    @Test
    void get() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected results equal the returned values of the get() method
        assertEquals(1, mllTest.get(0));
        assertEquals(2, mllTest.get(1));
        assertEquals(3, mllTest.get(2));
    }

    /**
     * Test set() method of {@code MyLinkedList} class.
     */
    @Test
    void set() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected results equal the returned values of the set() and get() method
        assertEquals(1, mllTest.set(0, 3));
        assertEquals(2, mllTest.set(1, 1));
        assertEquals(3, mllTest.set(2, 2));
        assertEquals(3, mllTest.get(0));
        assertEquals(1, mllTest.get(1));
        assertEquals(2, mllTest.get(2));
    }

    /**
     * Test second add() method of {@code MyLinkedList} class.
     */
    @Test
    void add1() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Use second add() method to add elements at a specified position
        mllTest.add(1, 4);
        mllTest.add(0, 5);

        // Check if the expected results equal the returned values of the get() method
        assertEquals(5, mllTest.get(0));
        assertEquals(1, mllTest.get(1));
        assertEquals(4, mllTest.get(2));
        assertEquals(2, mllTest.get(3));
        assertEquals(3, mllTest.get(4));
    }

    /**
     * Test second remove() method of {@code MyLinkedList} class.
     */
    @Test
    void remove1() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected results equal the return values of the called methods
        assertTrue(mllTest.remove((Integer) 1));
        assertEquals(2, mllTest.size());
    }

    /**
     * Test indexOf() method of {@code MyLinkedList} class.
     */
    @Test
    void indexOf() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected results equal the return values of the indexOf() method
        assertEquals(0, mllTest.indexOf(1));
        assertEquals(1, mllTest.indexOf(2));
        assertEquals(2, mllTest.indexOf(3));
        assertEquals(-1, mllTest.indexOf(4));
    }

    /**
     * Test lastIndexOf() method of {@code MyLinkedList} class.
     */
    @Test
    void lastIndexOf() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected results equal the return values of the lastIndexOf() method
        assertEquals(3, mllTest.lastIndexOf(1));
        assertEquals(4, mllTest.lastIndexOf(2));
        assertEquals(5, mllTest.lastIndexOf(3));
        assertEquals(-1, mllTest.lastIndexOf(4));
    }

    /**
     * Test listIterator() method of {@code MyLinkedList} class.
     */
    @Test
    void listIterator() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Get iterator of MyLinkedList
        ListIterator litrTest = mllTest.listIterator();

        // Check if the expected result equals the return value of the listIterator() method
        assertNotNull(litrTest);
    }

    /**
     * Test second listIterator() method of {@code MyLinkedList} class.
     */
    @Test
    void listIterator1() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Get iterator of MyLinkedList
        ListIterator litrTest = mllTest.listIterator(1);

        // Check if the expected result equals the return value of the iterator() method
        assertNotNull(litrTest);

        // Starting here we test the methods of the ListIterator of the MyLinkedList
        assertTrue(litrTest.hasNext());
        assertTrue(litrTest.hasPrevious());
        Exception oException = assertThrows(UnsupportedOperationException.class, () -> litrTest.add(4));
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());
        oException = assertThrows(UnsupportedOperationException.class, litrTest::remove);
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());
        oException = assertThrows(UnsupportedOperationException.class, () -> litrTest.set(4));
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());

        // Current position is element 2, next returns the current element
        assertEquals(2, litrTest.next());

        // The index of element 3 equals 2
        assertEquals(2, litrTest.nextIndex());

        // The previous element of 3 is 2
        assertEquals(2, litrTest.previous());

        // The index of element 1 equals 0
        assertEquals(0, litrTest.previousIndex());
    }

    /**
     * Test subList() method of {@code MyLinkedList} class.
     */
    @Test
    void subList() {
        // Create new MyLinkedList
        MyLinkedList<Integer> mllTest = new MyLinkedList<>();

        // Add some elements to the test list
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);
        mllTest.add(1);
        mllTest.add(2);
        mllTest.add(3);

        // Check if the expected results equal the "returned values" of the subList() method
        Exception oException = assertThrows(UnsupportedOperationException.class, () -> mllTest.subList(0, 2));
        assertEquals("The chosen operation is not supported. Please try another one.",
                oException.getMessage());
    }
}