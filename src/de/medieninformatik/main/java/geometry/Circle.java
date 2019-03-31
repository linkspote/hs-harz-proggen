package geometry;

/**
 * The <code>Circle</code> class extends the <code>Sphere</code> class and overrides some of its methods, e.g.
 * getArea. ATTENTION: A circle is a 2D shape.
 *
 * @author  Kai Gutsmann (m26667)
 * @author  Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Circle extends Sphere {
    /**
     * The <code>Circle</code> constructor just calls the constructor of its superior class.
     *
     * @param p_iRadius Represents the value for the radius attribute of this circle.
     */
    public Circle (int p_iRadius) {
        super(p_iRadius);
    }

    /**
     * This method returns the area of the current sphere using the corresponding formula of
     * 1/4 * PI * radius to the power of 2.
     *
     * @return Returns a double representing the area.
     */
    @Override
    public double getArea() {
        return super.getArea() / 4;
    }
}
