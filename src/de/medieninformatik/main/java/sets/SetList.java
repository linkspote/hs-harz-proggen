package sets;

import java.util.ArrayList;

/**
 * The <code>SetList</code> class extends the <code>ArrayList</code> class and overwrites the add methods to prevent
 * duplicates to be added to the set list.
 *
 * @param <T> Represents the type of elements in this list.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class SetList<T> extends ArrayList<T> {

    /**
     * The <code>SetList</code> constructor calls the constructor of its superior class and therefore creates an empty
     * set list with the default capacity of ten.
     */
    public SetList () {
        super();
    }

    /**
     * This method adds an element to the set list. Duplicates won't be added.
     * @param p_tGeneric Represents the element to add to the set list.
     * @return           Returns <code>true</code> if the set list does not already contain the element otherwise false
     *                   will be returned and the element won't be added.
     */
    @Override
    public boolean add(T p_tGeneric) {
        return (!contains(p_tGeneric)) && super.add(p_tGeneric);
    }

    /**
     * This method adds an element to the set list at the specified index. Duplicates won't be added.
     * @param p_iIndex   Represents the position of the element which shall be added to the set list.
     * @param p_tGeneric Represents the element to add to the set list.
     */
    @Override
    public void add(int p_iIndex, T p_tGeneric) {
        if (!contains(p_tGeneric))
            super.add(p_iIndex, p_tGeneric);
    }
}
