package sets;

/**
 * The <code>Sets</code> class provides 2 static methods: getIntersection and getUnion
 *
 * <p>This methods can be used to get 1 list out of 2 lists with different content of the same type.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Sets {
    /**
     * Fills the out list with elements which are part of the first as well as the second in list. The out list is
     * unsorted.
     *
     * <p>Example:
     * in1: Apple, Banana, Cherry, Dewberry
     * in2: Apple, Dewberry
     * out: Apple, Dewberry
     *
     * @param p_slIn1 Represents a <code>SetList</code> containing elements.
     * @param p_slIn2 Represents another <code>SetList</code> which which contains elements of the same type as the
     *                first one.
     * @param p_slOut Represents the <code>SetList</code> for the output and should be of the same type as the others.
     * @param <T>     Represents the type of elements in the lists.
     */
    public static <T> void getIntersection (SetList<? extends T> p_slIn1, SetList<? extends T> p_slIn2,
                                            SetList<? super T> p_slOut) {
        // For each generic of the first list
        for (T tGeneric : p_slIn1) {
            // Check if it also exists in the second list and if so add it to the out list
            if (p_slIn2.contains(tGeneric))
                p_slOut.add(tGeneric);
        }
    }

    /**
     * Fills the out list with elements which are part of the 2 in lists, but without creating duplicates.
     *
     * <p>Example:
     * in1: Apple, Banana, Cherry, Dewberry
     * in2: Apple, Dewberry, Lemon
     * out: Apple, Banana, Cherry, Dewberry, Lemon
     *
     * @param p_slIn1 Represents a <code>SetList</code> containing elements.
     * @param p_slIn2 Represents another <code>SetList</code> which which contains elements of the same type as the
     *                first one.
     * @param p_slOut Represents the <code>SetList</code> for the output and should be of the same type as the others.
     * @param <T>     Represents the type of elements in the lists.
     */
    public static <T> void getUnion (SetList<? extends T> p_slIn1, SetList<? extends T> p_slIn2,
                                     SetList<? super T> p_slOut) {
        // Add each generic of the first list to the out list
        for (T tGeneric : p_slIn1) {
            p_slOut.add(tGeneric);
        }

        // Add each generic of the second list also to the out list, a check for duplicates is provided by the add
        // method and is therefore not necessary at this point
        for (T t : p_slIn2) {
            p_slOut.add(t);
        }
    }
}
