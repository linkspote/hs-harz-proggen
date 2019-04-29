import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The {@code Main} class contains the main() method which represents the main execution point of each program.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Main {
    /**
     * This method is the main execution point of each program. Used to create a hashmap with characters from an example
     * file and their occurrence. This information is then used to generate a tree structure and calculate the Huffman
     * encoding for each respective character.
     *
     * @param args Represents a String array which contains parameters to configure the program execution.
     */
    public static void main (String[] args) {
        // Declare list for all lines contained in the text file
        List<String> lsLines = null;

        try {
            // Read file contents into a list of strings
            Path pFilePath = Paths.get("bsptree.txt");
            lsLines = Files.readAllLines(pFilePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Create hashmap for containing the occurrences of chars
        Map<Character, Integer> mCharOccurrence = new HashMap<>();
        // Check all lists
        for (int i = 0; i < lsLines.size(); i++) {
            String sLineContent = lsLines.get(i);

            // Get each individual char
            for (int j = 0; j < sLineContent.length(); j++) {
                char cChar = sLineContent.charAt(j);
                // Char is already in the map, increment occurrence counter accordingly
                if (mCharOccurrence.containsKey(cChar)) {
                    Integer iOccurrence = mCharOccurrence.get(cChar);
                    iOccurrence++;
                    mCharOccurrence.put(cChar, iOccurrence);
                }
                // Otherwise, add char and initialise the occurrence counter
                else {
                    mCharOccurrence.put(cChar, 1);
                }
            }
        }

        // Create priority queue to help build the tree structure
        PriorityQueue<Node> pqNode = new PriorityQueue<>(mCharOccurrence.size(), Comparator.comparing(Node::getOccurrence));
        // Add new nodes with all characters from the hashmap
        mCharOccurrence.forEach((cChar, iOccurrence) -> pqNode.add(new Node(null, null, iOccurrence, cChar)));

        // Create parent nodes with the nodes from the queue to the build a bottom up tree structure
        while (pqNode.size() > 1) {
            // Get first two nodes from the queue
            Node nOne = pqNode.poll();
            Node nTwo = pqNode.poll();

            // Offer new parent node containing previous elements as child nodes
            pqNode.offer(new Node(nOne, nTwo, nOne.getOccurrence() + nTwo.getOccurrence()));
        }

        // HashMap containing every character with its respective encoding
        HashMap<Character, String> mEncoding = new HashMap<>();
        calcEncoding(pqNode.poll(), "", mEncoding);

        // Print out the encoding table
        mEncoding.forEach((cChar, sEncoding) -> System.out.println("Zeichen: \"" + cChar + "\", Codierung: " + sEncoding));
    }

    /**
     * This method is used to traverse the tree structure and save the characters and their encoding in a hashmap.
     *
     * @param p_nNode Current node of traversal
     * @param p_sCurrentEncoding String that saves the encoding of an element in leaf node
     * @param p_mEncoding HashMap to store results
     */
    private static void calcEncoding (Node p_nNode, String p_sCurrentEncoding, HashMap<Character, String> p_mEncoding) {
        // If the node is a leaf node, store its content and its encoding as string
        if (p_nNode.getContent() != null) {
            p_mEncoding.put(p_nNode.getChar(), p_sCurrentEncoding);
        }
        // Check left and right child nodes
        else {
            calcEncoding(p_nNode.getLeft(), p_sCurrentEncoding + "0", p_mEncoding);
            calcEncoding(p_nNode.getRight(), p_sCurrentEncoding + "1", p_mEncoding);
        }
    }
}