package geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is used to check if the <code>Cuboid</code> and also the <code>Rectangle</code> class work as they
 * should. So cuboids and rectangle with the same values are equal even if they have a different order. Cuboids are not
 * equal to rectangles and the other way around. With the following tests this will be proven.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
class CuboidTest {
    /**
     * Tests the functionality of the getLength() method for the cuboids as well as the rectangles. The last ones just
     * inherit this method and therefore the test is not necessary but exists for demonstration purposes.
     */
    @Test
    void getLength() {
        Cuboid oCuboid = new Cuboid(5, 6, 7);
        Rectangle oRectangle = new Rectangle(6, 7);

        assertEquals(5, oCuboid.getLength());
        System.out.println("Length Cuboid: " + oCuboid.getLength());

        assertEquals(6, oRectangle.getLength());
        System.out.println("Length Rectangle: " + oRectangle.getLength());
    }

    /**
     * Tests the functionality of the getWidth() method for the cuboids as well as the rectangles. The last ones just
     * inherit this method and therefore the test is not necessary but exists for demonstration purposes.
     */
    @Test
    void getWidth() {
        Cuboid oCuboid = new Cuboid(5, 6, 7);
        Rectangle oRectangle = new Rectangle(6, 7);

        assertEquals(6, oCuboid.getWidth());
        System.out.println("Width Cuboid: " + oCuboid.getWidth());

        assertEquals(7, oRectangle.getWidth());
        System.out.println("Width Rectangle: " + oRectangle.getWidth());
    }

    /**
     * Tests the functionality of the getHeight() method for the cuboids as the constructor for rectangles does not
     * provide the height attribute.
     */
    @Test
    void getHeight() {
        Cuboid oCuboid = new Cuboid(5, 6, 7);

        assertEquals(7, oCuboid.getHeight());
        System.out.println("Height Cuboid: " + oCuboid.getHeight());
    }

    /**
     * Tests the functionality of the setLength() method for the cuboids as well as the rectangles. The last ones just
     * inherit this method and therefore the test is not necessary but exists for demonstration purposes.
     */
    @Test
    void setLength() {
        Cuboid oCuboid = new Cuboid(0, 6, 7);
        Rectangle oRectangle = new Rectangle(0, 7);

        oCuboid.setLength(5);
        oRectangle.setLength(6);

        assertEquals(5, oCuboid.getLength());
        System.out.println("Length Cuboid: " + oCuboid.getLength());

        assertEquals(6, oRectangle.getLength());
        System.out.println("Length Rectangle: " + oRectangle.getLength());
    }

    /**
     * Tests the functionality of the setWidth() method for the cuboids as well as the rectangles. The last ones just
     * inherit this method and therefore the test is not necessary but exists for demonstration purposes.
     */
    @Test
    void setWidth() {
        Cuboid oCuboid = new Cuboid(5, 0, 7);
        Rectangle oRectangle = new Rectangle(6, 0);

        oCuboid.setWidth(6);
        oRectangle.setWidth(7);

        assertEquals(6, oCuboid.getWidth());
        System.out.println("Width Cuboid: " + oCuboid.getWidth());

        assertEquals(7, oRectangle.getWidth());
        System.out.println("Width Rectangle: " + oRectangle.getWidth());
    }

    /**
     * Tests the functionality of the setHeight() method for the cuboids as the constructor for rectangles does not
     * provide the height attribute.
     */
    @Test
    void setHeight() {
        Cuboid oCuboid = new Cuboid(5, 6, 0);

        oCuboid.setHeight(7);

        assertEquals(7, oCuboid.getHeight());
        System.out.println("Height Cuboid: " + oCuboid.getHeight());
    }

    /**
     * Tests the functionality of the equals() method for the cuboids as well as the rectangles. It shows that cuboids
     * are not rectangles and the other way around. Despite that it also shows that the order of the values doesn't
     * matter if they are equal.
     */
    @Test
    void equals1() {
        Cuboid oCuboid = new Cuboid(5, 6, 7);
        Cuboid oCuboid2 = new Cuboid(5, 6, 7);
        Cuboid oCuboid3 = new Cuboid(7, 6, 5);
        Cuboid oCuboid4 = new Cuboid(1, 2, 0);
        Rectangle oRectangle = new Rectangle(6, 7);
        Rectangle oRectangle2 = new Rectangle(6, 7);
        Rectangle oRectangle3 = new Rectangle(7, 6);
        Rectangle oRectangle4 = new Rectangle(1, 2);

        assertEquals(oCuboid, oCuboid);
        System.out.println("Cuboid == Cuboid: " + oCuboid.equals(oCuboid));

        assertEquals(oCuboid, oCuboid);
        System.out.println("Cuboid == Cuboid2: " + oCuboid.equals(oCuboid2));

        assertEquals(oCuboid, oCuboid);
        System.out.println("Cuboid == Cuboid3: " + oCuboid.equals(oCuboid3));

        assertNotEquals(oCuboid, oCuboid4);
        System.out.println("Cuboid == Cuboid4: " + oCuboid.equals(oCuboid4));

        assertNotEquals(oCuboid, oRectangle);
        System.out.println("Cuboid4 == Rectangle4: " + oCuboid4.equals(oRectangle4));

        assertEquals(oRectangle, oRectangle);
        System.out.println("Rectangle == Rectangle: " + oRectangle.equals(oRectangle));

        assertEquals(oRectangle, oRectangle2);
        System.out.println("Rectangle == Rectangle2: " + oRectangle.equals(oRectangle2));

        assertEquals(oRectangle, oRectangle3);
        System.out.println("Rectangle == Rectangle3: " + oRectangle.equals(oRectangle3));

        assertNotEquals(oRectangle, oRectangle4);
        System.out.println("Rectangle == Rectangle4: " + oRectangle.equals(oRectangle4));

        assertNotEquals(oRectangle4, oCuboid4);
        System.out.println("Rectangle4 == Cuboid4: " + oRectangle4.equals(oCuboid4));
    }
}