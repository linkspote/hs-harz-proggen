package sets;

import geometry.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is used to check if the <code>SetList</code> class works as it should. So when you try to add an
 * element the second time it won't be added because the set list prevents duplicates to be added.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
class SetListTest {
    /**
     * Tests the functionality of the add() method which ensures that you can't add duplicates to set lists.
     */
    @Test
    void add() {
        SetList slTest = new SetList();
        slTest.add(new Cuboid(5, 5, 5));
        slTest.add(new Cuboid(5, 5, 5));
        slTest.add(new Rectangle(6, 6));
        slTest.add(new Rectangle(6, 6));
        slTest.add(new Sphere(7));
        slTest.add(new Sphere(7));
        slTest.add(new Circle(8));
        slTest.add(new Circle(8));

        assertEquals(4, slTest.size());
        System.out.println("Size: " + slTest.size());

        assertEquals("class geometry.Cuboid", slTest.get(0).getClass().toString());
        System.out.println("Class of index 0 is Cuboid?: " + slTest.get(0).getClass().toString());

        assertEquals("class geometry.Rectangle", slTest.get(1).getClass().toString());
        System.out.println("Class of index 1 is Rectangle?: " + slTest.get(1).getClass().toString());

        assertEquals("class geometry.Sphere", slTest.get(2).getClass().toString());
        System.out.println("Class of index 2 is Sphere?: " + slTest.get(2).getClass().toString());

        assertEquals("class geometry.Circle", slTest.get(3).getClass().toString());
        System.out.println("Class of index 3 is Circle?: " + slTest.get(3).getClass().toString());
    }

    /**
     * Tests the functionality of the second add() method where you can use an index to say where to put the new element
     * to. It also ensures that you can't add duplicates to set lists.
     */
    @Test
    void add1() {
        SetList slTest = new SetList();
        slTest.add(0, new Cuboid(5, 5, 5));
        slTest.add(1, new Cuboid(5, 5, 5));
        assertEquals(1, slTest.size());
        System.out.println("Size: " + slTest.size());

        slTest.add(1, new Rectangle(6, 6));
        slTest.add(2, new Rectangle(6, 6));
        assertEquals(2, slTest.size());
        System.out.println("Size: " + slTest.size());

        slTest.add(2, new Sphere(7));
        slTest.add(3, new Sphere(7));
        assertEquals(3, slTest.size());
        System.out.println("Size: " + slTest.size());

        slTest.add(3, new Circle(8));
        slTest.add(4, new Circle(8));
        assertEquals(4, slTest.size());
        System.out.println("Size: " + slTest.size());

        assertEquals("class geometry.Cuboid", slTest.get(0).getClass().toString());
        System.out.println("Class of index 0 is Cuboid?: " + slTest.get(0).getClass().toString());

        assertEquals("class geometry.Rectangle", slTest.get(1).getClass().toString());
        System.out.println("Class of index 1 is Rectangle?: " + slTest.get(1).getClass().toString());

        assertEquals("class geometry.Sphere", slTest.get(2).getClass().toString());
        System.out.println("Class of index 2 is Sphere?: " + slTest.get(2).getClass().toString());

        assertEquals("class geometry.Circle", slTest.get(3).getClass().toString());
        System.out.println("Class of index 3 is Circle?: " + slTest.get(3).getClass().toString());
    }
}