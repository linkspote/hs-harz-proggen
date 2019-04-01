package geometry;

/**
 * The <code>Circle</code> class extends the <code>Sphere</code> class. ATTENTION: A circle is a 2D shape.
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
}
