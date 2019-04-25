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
        try {
            Path pFilePath = Paths.get("bsptree.txt");
            List<String> lsLines = Files.readAllLines(pFilePath);

            Map<Character, Integer> mCharOccurrence = new HashMap<>();

            for (int i = 0; i < lsLines.size(); i++) {
                String sLineContent = lsLines.get(i);

                for (int j = 0; j < sLineContent.length(); j++) {
                    char cChar = sLineContent.charAt(j);

                    if (mCharOccurrence.containsKey(cChar)) {
                        Integer iOccurrence = mCharOccurrence.get(cChar);
                        iOccurrence++;
                        mCharOccurrence.put(cChar, iOccurrence);
                    }
                    else {
                        mCharOccurrence.put(cChar, 1);
                    }
                }
            }

            PriorityQueue<Node> pqNode = new PriorityQueue<>(mCharOccurrence.size(), Comparator.comparing(Node::getOccurrence));

            mCharOccurrence.forEach((cChar, iOccurrence) -> pqNode.add(new Node(null, null, iOccurrence, cChar)));

            while (pqNode.size() > 1) {
                Node nOne = pqNode.poll();
                Node nTwo = pqNode.poll();
                pqNode.offer(new Node(nOne, nTwo, null));
            }

            HashMap<Character, String> mEncoding = new HashMap<>();
            calcEncoding(pqNode.poll(), "", mEncoding);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calcEncoding (Node p_nNode, String p_sCurrentEncoding, HashMap<Character, String> p_mEncoding) {
        if (p_nNode.getContent() != null) {
            p_mEncoding.put(p_nNode.getChar(), p_sCurrentEncoding);
        }
        else {
            calcEncoding(p_nNode.getLeft(), p_sCurrentEncoding + "0", p_mEncoding);
            calcEncoding(p_nNode.getRight(), p_sCurrentEncoding + "1", p_mEncoding);
        }
    }
}
