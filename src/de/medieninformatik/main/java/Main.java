import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// TODO: 25.04.2019 - Adjust existing comments and add some where necessary
// TODO: 25.04.2019 - Don*t forget to add some output by printing the nodes of the final map 
/**
 * The {@code Main} class contains the main() method which represents the main execution point of each program.
 *
 * <p>ATTENTION: This program ist just an example!
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Main {
    /**
     * This method is the main execution point of each program.
     *
     * @param args Represents a String array which contains parameters to configure the program execution.
     */
    public static void main (String[] args) {
        List<String> lsLines = null;
        try {
            // read file contents into a List of Strings
            Path pFilePath = Paths.get("bsptree.txt");
            lsLines = Files.readAllLines(pFilePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // Create HashMap for containing the occurrences of chars
        Map<Character, Integer> mCharOccurrence = new HashMap<>();
        // Check all Lists
        for (int i = 0; i < lsLines.size(); i++) {
            String sLineContent = lsLines.get(i);
            // Get each individual char
            for (int j = 0; j < sLineContent.length(); j++) {
                char cChar = sLineContent.charAt(j);
                // char is already in the map, increment occurrence counter accordingly
                if (mCharOccurrence.containsKey(cChar)) {
                    Integer iOccurrence = mCharOccurrence.get(cChar);
                    iOccurrence++;
                    mCharOccurrence.put(cChar, iOccurrence);
                }
                // otherwise, add char and initialise the occurrence counter
                else {
                    mCharOccurrence.put(cChar, 1);
                }
            }
        }
        // Create Priority Queue to help build the tree structure
        PriorityQueue<Node> pqNode = new PriorityQueue<>(mCharOccurrence.size(), Comparator.comparing(Node::getOccurrence));
        // Add new Nodes with all characters from the HashMap
        mCharOccurrence.forEach((cChar, iOccurrence) -> pqNode.add(new Node(null, null, iOccurrence, cChar)));
        // Create parent Nodes with the Nodes from the Queue to the build a bottom up tree structure
        while (pqNode.size() > 1) {
            // Get first two Nodes from the Queue
            Node nOne = pqNode.poll();
            Node nTwo = pqNode.poll();
            // Offer new parent Node containing previous Elements as child Nodes
            pqNode.offer(new Node(nOne, nTwo, null, nOne.getOccurrence()+nTwo.getOccurrence()));
        }
        // HashMap containing every character with its respective encoding
        HashMap<Character, String> mEncoding = new HashMap<>();
        calcEncoding(pqNode.poll(), "", mEncoding);
        // print out the encoding table
        System.out.println(mEncoding);
    }

    /**
     * This method is used to traverse the tree structure and save the characters and their encoding in a HashMap.
     * @param p_nNode Current Node of traversion
     * @param p_sCurrentEncoding String that saves the encoding of an element in leaf Node
     * @param p_mEncoding HashMap to store results
     */
    private static void calcEncoding (Node p_nNode, String p_sCurrentEncoding, HashMap<Character, String> p_mEncoding) {
        // If the Node is a leaf Node, store its content and its encoding as String
        if (p_nNode.getContent() != null) {
            p_mEncoding.put(p_nNode.getChar(), p_sCurrentEncoding);
        }
        // check left and right child Nodes
        else {
            calcEncoding(p_nNode.getLeft(), p_sCurrentEncoding + "0", p_mEncoding);
            calcEncoding(p_nNode.getRight(), p_sCurrentEncoding + "1", p_mEncoding);
        }
    }
}