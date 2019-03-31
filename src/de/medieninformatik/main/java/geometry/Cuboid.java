package geometry;

/**
 * The <code>Cuboid</code> class extends the abstract <code>GeoObject</code> class which forces subclasses to implement
 * own versions of the getArea and equals method. ATTENTION: A cuboid is a 3D shape.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Cuboid extends GeoObject {
    // Declare class attributes
    protected int iLength, iWidth, iHeight;

    /**
     * The <code>Cuboid</code> constructor sets the initial values for the attributes.
     *
     * @param p_iLength Represents the value for the length attribute of this cuboid.
     * @param p_iWidth  Represents the value for the width attribute of this cuboid.
     * @param p_iHeight Represents the value for the height attribute of this cuboid.
     */
    public Cuboid (int p_iLength, int p_iWidth, int p_iHeight) {
        iLength = p_iLength;
        iWidth = p_iWidth;
        iHeight = p_iHeight;
    }

    /**
     * This method returns the value of the length attribute which is set for the current cuboid.
     *
     * @return Returns an int representing the length.
     */
    public int getLength() {
        return iLength;
    }

    /**
     * This method returns the value of the width attribute which is set for the current cuboid.
     *
     * @return Returns an int representing the width.
     */
    public int getWidth() {
        return iWidth;
    }

    /**
     * This method returns the value of the height attribute which is set for the current cuboid.
     *
     * @return Returns an int representing the height.
     */
    public int getHeight() {
        return iHeight;
    }

    /**
     * This method sets the value of the length attribute for the current cuboid.
     *
     * @param p_iLength Represents the new value of the length attribute.
     */
    public void setLength(int p_iLength) {
        this.iLength = p_iLength;
    }

    /**
     * This method sets the value of the width attribute for the current cuboid.
     *
     * @param p_iWidth Represents the new value of the width attribute.
     */
    public void setWidth(int p_iWidth) {
        this.iWidth = p_iWidth;
    }

    /**
     * This method sets the value of the height attribute for the current cuboid.
     *
     * @param p_iHeight Represents the new value of the height attribute.
     */
    public void setHeight(int p_iHeight) {
        this.iHeight = p_iHeight;
    }

    /**
     * This method returns the area of the current cuboid using the corresponding formula of 2 * (length * width + width
     * * height + length * height).
     *
     * @return Returns a double representing the area.
     */
    @Override
    public double getArea() {
        return 2 * (iLength * iWidth + iWidth * iHeight + iLength * iHeight);
    }

    /**
     * This method can be used to check whether the current cuboid is equal to another one or not.
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
            // Cast other object to cuboid
            Cuboid oOther = (Cuboid) p_oOther;
            // Set value of bool comparing the class attributes
            bResult = (Integer.compare(iLength, oOther.iLength) == 0 && Integer.compare(iWidth, oOther.iWidth) == 0 &&
                    Integer.compare(iHeight, oOther.iHeight) == 0);
        }

        // Return either true when they are equal or false if they are unequal
        return bResult;
    }
}
