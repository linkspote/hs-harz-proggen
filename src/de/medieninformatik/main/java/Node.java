// TODO: 25.04.2019 - Add comments to everything

/**
 * This Class is used to create <code>Node</code> Elements, which are then used to build a tree data structure.
 */
public class Node {
    // two separate sub nodes
    private Node nLeft, nRight;
    // Integer containing number of occurrence
    private int iOccurrence;
    // private char cChar
    // Content Node of given Node object
    private ContentNode cnContent;

    /**
     * This class provides a content Node, which stores a character.
     */
    private class ContentNode {
        private char cChar;
        public ContentNode (char p_cChar) {
            cChar = p_cChar;
        }
    }

    public Node (Node p_nLeft, Node p_nRight, ContentNode p_cnContent, int p_iOccurrence) {
        nLeft = p_nLeft;
        nRight = p_nRight;
        cnContent = p_cnContent;
        iOccurrence = p_iOccurrence;
    }

    public Node (Node p_nLeft, Node p_nRight, int p_iOccurrence, char p_cChar) {
        nLeft = p_nLeft;
        nRight = p_nRight;
        iOccurrence = p_iOccurrence;
        cnContent = new ContentNode(p_cChar);
    }

    /**
     * Returns a textual representation of the <code>Node</code> object, which contains the number of occurrence and the
     * given character stored in the <code>Node</code>.
     * @return String representation of given object
     */
    @Override
    public String toString() {
        return "Knoten {" +
                "haeufigkeitDesZeichen=" + iOccurrence +
                ", zeichen=" + cnContent.cChar +
                "}";
    }

    /**
     * Access left sub Node.
     * @return Reference to the left sub node
     */
    public Node getLeft() {
        return nLeft;
    }

    /**
     * Access right sub Node.
     * @return Reference to the right sub node
     */
    public Node getRight() {
        return nRight;
    }

    /**
     * Returns the counter for how often an element is present.
     * @return Number of occurrence as Integer
     */
    public int getOccurrence() {
        return iOccurrence;
    }

    /**
     * Returns the character that is stored in the <code>ContentNode</code>.
     * @return Stored char in Node
     */
    public char getChar() {
        return cnContent.cChar;
    }

    /**
     * Get a reference to the <code>ContentNode</code> object.
     * @return Reference to Content Node
     */
    public ContentNode getContent() {
        return cnContent;
    }
}
