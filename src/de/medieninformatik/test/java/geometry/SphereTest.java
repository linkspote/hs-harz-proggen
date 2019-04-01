package geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is used to check if the <code>Sphere</code> and also the <code>Circle</code> class work as they
 * should. Spheres are not equal to circles and the other way around. With the following tests this will be proven.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
class SphereTest {
    /**
     * Tests the functionality of the getRadius() method for the spheres as well as the circles. The last ones just
     * inherit this method and therefore the test is not necessary but exists for demonstration purposes.
     */
    @Test
    void getRadius() {
        Sphere oSphere = new Sphere(5);
        Circle oCircle = new Circle(6);

        assertEquals(5, oSphere.getRadius());
        System.out.println("Radius Sphere: " + oSphere.getRadius());

        assertEquals(6, oCircle.getRadius());
        System.out.println("Radius Circle: " + oCircle.getRadius());
    }

    /**
     * Tests the functionality of the setRadius() method for the spheres as well as the circles. The last ones just
     * inherit this method and therefore the test is not necessary but exists for demonstration purposes.
     */
    @Test
    void setRadius() {
        Sphere oSphere = new Sphere(0);
        Circle oCircle = new Circle(0);

        oSphere.setRadius(7);
        oCircle.setRadius(8);

        assertEquals(7, oSphere.getRadius());
        System.out.println("Radius Sphere: " + oSphere.getRadius());

        assertEquals(8, oCircle.getRadius());
        System.out.println("Radius Circle: " + oCircle.getRadius());
    }

    /**
     * Tests the functionality of the equals() method for the spheres as well as the circles. It shows that spheres
     * are not circles and the other way around.
     */
    @Test
    void equals1() {
        Sphere oSphere = new Sphere(5);
        Sphere oSphere2 = new Sphere(5);
        Sphere oSphere3 = new Sphere(6);
        Circle oCircle = new Circle(6);
        Circle oCircle2 = new Circle(6);
        Circle oCircle3 = new Circle(5);

        assertEquals(oSphere, oSphere);
        System.out.println("Sphere == Sphere: " + oSphere.equals(oSphere));

        assertEquals(oSphere, oSphere2);
        System.out.println("Sphere == Sphere2: " + oSphere.equals(oSphere2));

        assertNotEquals(oSphere, oSphere3);
        System.out.println("Sphere == Sphere3: " + oSphere.equals(oSphere3));

        assertNotEquals(oSphere, oCircle);
        System.out.println("Sphere == Circle: " + oSphere.equals(oCircle));

        assertEquals(oCircle, oCircle);
        System.out.println("Circle == Circle: " + oCircle.equals(oCircle));

        assertEquals(oCircle, oCircle2);
        System.out.println("Circle == Circle2: " + oCircle.equals(oCircle2));

        assertNotEquals(oCircle, oCircle3);
        System.out.println("Circle == Circle3: " + oCircle.equals(oCircle3));

        assertNotEquals(oCircle, oSphere);
        System.out.println("Circle == Sphere: " + oCircle.equals(oSphere));
    }
}