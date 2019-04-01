package sets;

import geometry.Circle;
import geometry.Cuboid;
import geometry.Rectangle;
import geometry.Sphere;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is used to check if the <code>Sets</code> class works as is should. It provides two static methods.
 * One to get the intersection set and one to get the union set out of 2 set lists. The results will be written into a
 * third list.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
class SetsTest {
    /**
     * Tests the functionality of the getIntersection() method which writes the intersection set of two set lists into
     * an output set list.
     */
    @Test
    void getIntersection() {
        SetList slIn1 = new SetList(), slIn2 = new SetList(), slOut = new SetList();

        slIn1.add(new Circle(5));
        slIn1.add(new Circle(1));
        slIn1.add(new Cuboid(5, 5, 9));
        slIn1.add(new Cuboid(1, 2, 3));

        slIn2.add(new Circle(5));
        slIn2.add(new Sphere(12));
        slIn2.add(new Rectangle(1, 2));
        slIn2.add(new Cuboid(1, 2,3));

        Sets.getIntersection(slIn1, slIn2, slOut);

        assertEquals(2, slOut.size());
        System.out.println("Size: " + slOut.size());

        assertEquals("class geometry.Circle", slOut.get(0).getClass().toString());
        System.out.println("Class of index 0 is Circle?: " + slOut.get(0).getClass().toString());

        assertEquals("class geometry.Cuboid", slOut.get(1).getClass().toString());
        System.out.println("Class of index 1 is Cuboid?: " + slOut.get(1).getClass().toString());
    }

    /**
     * Tests the functionality of the getUnion() method which writes the union set of two set lists into
     * an output set list.
     */
    @Test
    void getUnion() {
        SetList slIn1 = new SetList(), slIn2 = new SetList(), slOut = new SetList();

        slIn1.add(new Circle(5));
        slIn1.add(new Circle(1));
        slIn1.add(new Cuboid(5, 5, 9));
        slIn1.add(new Cuboid(1, 2, 3));

        slIn2.add(new Circle(5));
        slIn2.add(new Sphere(12));
        slIn2.add(new Rectangle(1, 2));
        slIn2.add(new Cuboid(1, 2,3));

        Sets.getUnion(slIn1, slIn2, slOut);

        assertEquals(6, slOut.size());
        System.out.println("Size: " + slOut.size());

        assertEquals("class geometry.Circle", slOut.get(0).getClass().toString());
        System.out.println("Class of index 0 is Circle?: " + slOut.get(0).getClass().toString());

        assertEquals("class geometry.Circle", slOut.get(1).getClass().toString());
        System.out.println("Class of index 1 is Circle?: " + slOut.get(1).getClass().toString());

        assertEquals("class geometry.Cuboid", slOut.get(2).getClass().toString());
        System.out.println("Class of index 2 is Cuboid?: " + slOut.get(2).getClass().toString());

        assertEquals("class geometry.Cuboid", slOut.get(3).getClass().toString());
        System.out.println("Class of index 3 is Cuboid?: " + slOut.get(3).getClass().toString());

        assertEquals("class geometry.Sphere", slOut.get(4).getClass().toString());
        System.out.println("Class of index 4 is Sphere?: " + slOut.get(4).getClass().toString());

        assertEquals("class geometry.Rectangle", slOut.get(5).getClass().toString());
        System.out.println("Class of index 5 is Rectangle?: " + slOut.get(5).getClass().toString());
    }
}