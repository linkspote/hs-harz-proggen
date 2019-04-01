package geometry;

/**
 * The <code>Sphere</code> class extends the abstract <code>GeoObject</code> class which forces subclasses to implement
 * own versions of the equals method. ATTENTION: A sphere is a 3D shape.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Sphere extends GeoObject {
    // Declare class attributes
    protected int iRadius;

    /**
     * The <code>Sphere</code> constructor sets the initial values for the attributes.
     *
     * @param p_iRadius Represents the value for the radius attribute of this sphere.
     */
    public Sphere (int p_iRadius) {
        iRadius = p_iRadius;
    }

    /**
     * This method returns the value of the radius attribute which is set for the current sphere.
     *
     * @return Returns an int representing the radius.
     */
    public int getRadius() {
        return iRadius;
    }

    /**
     * This method sets the value of the radius attribute for the current sphere.
     *
     * @param p_iRadius Represents the new value of the radius attribute.
     */
    public void setRadius(int p_iRadius) {
        this.iRadius = p_iRadius;
    }

    /**
     * This method can be used to check whether the current sphere is equal to another one or not.
     *
     * @param p_oOther Represents the other object to compare the current one with.
     * @return         Returns <code>true</code> if the values of both are equal, the other object is not null and both
     *                 belong to the same class otherwise it will return <code>false</code>.
     */
    @Override
    public boolean equals(Object p_oOther) {
        // Initiate bool with default value
        boolean bResult = false;

        // Check if the other object does not contain a null reference and that they are of the same class
        if (p_oOther != null && getClass() == p_oOther.getClass()) {
            // Cast other object to sphere
            Sphere oOther = (Sphere) p_oOther;
            // Set value of bool comparing the class attributes
            bResult = (Integer.compare(iRadius, oOther.iRadius) == 0);
        }

        // Return either true when they are equal or false if they are unequal
        return bResult;
    }
}
