package geometry;

/**
 * The <code>Rectangle</code> class extends the <code>Cuboid</code> class and overrides some of its methods, e.g.
 * getArea. ATTENTION: The height for this geometrical object is zero as its only a 2D shape.
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

    /**
     * This method returns the area of the current rectangle using the corresponding formula of length * width.
     *
     * @return Returns a double representing the area.
     */
    @Override
    public double getArea() {
        return super.iLength * super.iWidth;
    }
}
