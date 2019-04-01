package geometry;

/**
 * The <code>GeoObject</code> class is used to force subclasses to implement necessary methods. This class cannot be
 * instantiated as it is abstract.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public abstract class GeoObject {
    /**
     * This method returns a boolean based on the equality of the compared objects.
     *
     * @param p_oOther Represents the other object to compare the current one with.
     * @return         Returns <code>true</code> if the values of both are equal, the other object is not null and both
     *                 belong to the same class otherwise it will return <code>false</code>.
     */
    @Override
    public abstract boolean equals(Object p_oOther);
}
