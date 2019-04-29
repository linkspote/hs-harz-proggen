/**
 * This class is used to create {@code Node} elements which hold a character and a counter for their occurrence.
 * They are used to build a tree data structure.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Node {
    // Two separate sub nodes
    private Node nLeft, nRight;
    // Integer containing number of occurrence
    private int iOccurrence;
    // Content node of given node object
    private ContentNode cnContent;

    /**
     * This class provides a content node, which stores a character.
     */
    private class ContentNode {
        // Character that is stored by this class
        private char cChar;

        /**
         * Constructor of {@code ContentNode} class. Allows to initialise the character that is stored.
         *
         * @param p_cChar Character to initialise
         */
        public ContentNode (char p_cChar) {
            cChar = p_cChar;
        }
    }

    /**
     * Constructor of class {@code Node}. Allows to assign left and right child nodes and set a occurrence value.
     *
     * @param p_nLeft Left child node
     * @param p_nRight Right child node
     * @param p_iOccurrence Occurrence counter as integer
     */
    public Node (Node p_nLeft, Node p_nRight, int p_iOccurrence) {
        nLeft = p_nLeft;
        nRight = p_nRight;
        iOccurrence = p_iOccurrence;
    }

    /**
     * Secondary constructor of the {@code Node} class. Allows to assign left/right child nodes, set the occurrence
     * value and a character that will be stored in the content node.
     *
     * @param p_nLeft Left child node
     * @param p_nRight Right child node
     * @param p_iOccurrence Occurrence counter as integer
     * @param p_cChar Char to be stored in the content node
     */
    public Node (Node p_nLeft, Node p_nRight, int p_iOccurrence, char p_cChar) {
        this(p_nLeft, p_nRight, p_iOccurrence);
        cnContent = new ContentNode(p_cChar);
    }

    /**
     * Returns a textual representation of the {@code Node} object, which contains the amount of occurrences and the
     * given character stored in the {@code Node}.
     *
     * @return Returns a string representing the given object
     */
    @Override
    public String toString() {
        return "Knoten {" +
                "Haeufigkeit des Zeichens: " + iOccurrence +
                ", Zeichen: " + cnContent.cChar +
                "}";
    }

    /**
     * Access left sub node.
     *
     * @return Returns a reference to the left sub node
     */
    public Node getLeft() {
        return nLeft;
    }

    /**
     * Access right sub node.
     *
     * @return Returns a reference to the right sub node
     */
    public Node getRight() {
        return nRight;
    }

    /**
     * Returns the counter for how often an element is present.
     *
     * @return Returns the amount of occurrences as integer
     */
    public int getOccurrence() {
        return iOccurrence;
    }

    /**
     * Returns the character that is stored in the {@code ContentNode}.
     *
     * @return Returns the current nodes char
     */
    public char getChar() {
        return cnContent.cChar;
    }

    /**
     * Get a reference to the <code>ContentNode</code> object.
     *
     * @return Returns a reference to this nodes {@code ContentNode}
     */
    public ContentNode getContent() {
        return cnContent;
    }
}
