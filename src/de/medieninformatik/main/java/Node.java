// TODO: 25.04.2019 - Add comments to everything
public class Node {
    private Node nLeft, nRight;
    private int iOccurrence;
    private char cChar;
    private ContentNode cnContent;

    private class ContentNode {
        private char cChar;

        public ContentNode (char p_cChar) {
            cChar = p_cChar;
        }
    }

    public Node (Node p_nLeft, Node p_nRight, ContentNode p_cnContent) {
        nLeft = p_nLeft;
        nRight = p_nRight;
        cnContent = p_cnContent;
    }

    public Node (Node p_nLeft, Node p_nRight, int p_iOccurrence, char p_cChar) {
        nLeft = p_nLeft;
        nRight = p_nRight;
        iOccurrence = p_iOccurrence;
        cnContent = new ContentNode(p_cChar);
    }

    @Override
    public String toString() {
        return "Knoten {" +
                "haeufigkeitDesZeichen=" + iOccurrence +
                ", zeichen=" + cnContent.cChar +
                "}";
    }

    public Node getLeft() {
        return nLeft;
    }

    public Node getRight() {
        return nRight;
    }

    public int getOccurrence() {
        return iOccurrence;
    }

    public char getChar() {
        return cnContent.cChar;
    }

    public ContentNode getContent() {
        return cnContent;
    }
}
