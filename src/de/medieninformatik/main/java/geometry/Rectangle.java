package geometry;

/**
 * The <code>Rectangle</code> class extends the <code>Cuboid</code> class.
 * ATTENTION: The height for this geometrical object is zero as its only a 2D shape.
 *
 * @author  Kai Gutsmann (m26667)
 * @author  Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Rectangle extends Cuboid {
    /**
     * The <code>Rectangle</code> constructor just calls the constructor of its superior class.
     *
     * @param p_iLength Represents the value for the length attribute of this rectangle.
     * @param p_iWidth  Represents the value for the width attribute of this rectangle.
     */
    public Rectangle (int p_iLength, int p_iWidth) {
        super(p_iLength, p_iWidth, 0);
    }
}
