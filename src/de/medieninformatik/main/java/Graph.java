import java.io.IOException;
import java.util.*;

/**
 * The {@code Graph} class is used to store the {@code Edge}s and vertices (see {@link Vertex}) for the Kruskal
 * algorithm in one place. In the GUI window it equals the content of the window itself without highlighting the
 * minimum spanning tree.
 */
public class Graph {
    // Declare class attributes
    private HashSet<Vertex> vertices;
    private HashSet<Edge> edges;

    /**
     * Constructs an empty {@code Graph}.
     */
    public Graph() {
        // Initialize empty class attributes
        this.vertices = new HashSet<>();
        this.edges    = new HashSet<>();
    }

    /**
     * Constructs a {@code Graph} which contains the given {@code Vertex} but not a single {@code Edge}.
     *
     * @param v Represents the {@code Vertex} for the {@code Graph}.
     */
    public Graph(Vertex v) {
        // Call first constructor to init class attributes
        this();
        // Add given vertex to vertices hash set
        this.vertices.add(v);
    }

    /**
     * Constructs a {@code Graph} which contains the given {@code Set} of {@code Edge}s and therefore also a couple of
     * {@code Vertex} objects.
     *
     * @param edges Represents a {@code Set} of {@code Edge}s for the {@code Graph}.
     */
    public Graph(Set<Edge> edges) {
        // Initialize empty class attributes
        this();
        // Add all edges of the given set to the edges hash set of this graph
        this.edges.addAll(edges);
        // Add the start and end vertex of the given edges to the vertices hash set
        this.edges.forEach( e -> { // Da HashSet, wird wiederholtes Einfuegen desselben Vertex ignoriert
            vertices.add(e.getStartVertex());
            vertices.add(e.getEndVertex());
        });
    }

    /**
     * Returns the number of vertices (see {@link Vertex}) of this {@code Graph}.
     *
     * @return The number of vertices (see {@link Vertex}) of this {@code Graph} as integer value.
     */
    public int numberOfVertices() {
        return vertices.size();
    }

    /**
     * Checks whether the current {@code Graph} contains the given {@code Vertex} and returns true if that's the case.
     *
     * @param v Represents the {@code Vertex} to be checked.
     * @return True if the current {@code Graph} contains the given {@code Vertex}.
     */
    public boolean contains(Vertex v) {
        return vertices.contains(v);
    }

    /**
     * Returns the vertices (see {@link Vertex}) of the current {@code Graph}.
     *
     * @return The vertices (see {@link Vertex}) of the current {@code Graph} as a {@code Set} of vertices.
     */
    public Set<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Returns the {@code Edge}s of the current {@code Graph}.
     *
     * @return The {@code Edge}s of the current {@code Graph} as a {@code Set} of edges.
     */
    public Set<Edge> getEdges() {
        return edges;
    }

    /**
     * Loops through the given {@code List} to create a new {@code Graph} based on the content of the mentioned list
     * which contains {@code Edge}s and vertices (see {@link Vertex}).
     *
     * @param lines Represents a {@code List} which contains information about vertices (see {@link Vertex}) and
     *              {@code Edge}s for a {@code Graph}.
     * @return A new {@code Graph} containing {@code Edge}s and vertices (see {@link Vertex}).
     * @throws IOException Informs the user about not enough or too many arguments for creating a new {@code Edge}.
     */
    public static Graph read(List<String> lines) throws IOException {
        // Zuerst: Lies Vertices aus den Zeilen ein
        // Get amount of entries in list lines, create new array list to store vertices in and init a counter for the
        // vertex ids
        int n = lines.size();
        ArrayList<Vertex> vertices = new ArrayList<>(n);
        int i = 0;

        // Loop through each entry of the lines list
        for(String s : lines) {
            // Ignore all lines starting with a hash tag
            if(s.startsWith("#")) continue; // Kommentarzeilen uberlesen.
            // Init new string tokenizer for the content of a line
            StringTokenizer tok = new StringTokenizer(s);

            // Check if line contains more than one character (whitespace doesn't count)
            if(tok.countTokens() > 1) {
                // Add new vertex to vertices array list with i as id for the vertex and the token values as x and y
                // positions
                vertices.add(new Vertex(i++,
                        Integer.valueOf(tok.nextToken()),
                        Integer.valueOf(tok.nextToken())
                ));
            }
        }

        // Finde die gewichteten Kanten zu jedem Vertex
        // Create a new edges hash set, reset the counter and declare a weight variable for the edges
        Set<Edge> edges = new HashSet<>();
        i = 0;
        double weight;

        // Loop through each entry of the lines list
        for(String s : lines) {
            // Ignore all lines starting with a hash tag
            if(s.startsWith("#")) continue; // Kommentarzeilen uberlesen.
            // Init a new string tokenizer for the content of a line
            StringTokenizer tok = new StringTokenizer(s);

            // If the amount of tokens in the current line is odd, throw an io exception
            if(tok.countTokens() %2 == 1) throw new IOException("ungerade Anzahl von Elementen!");

            // Declare a variable for the tokens of the start and end vertex values
            String t;

            // Check if line contains more than one character (whitespace doesn't count)
            if(tok.countTokens() > 1) {
                // Get start vertex of upcoming edges
                Vertex start = vertices.get(i++);

                // Ignore x and y position
                t = tok.nextToken(); // x ueberlesen
                t = tok.nextToken(); // y ueberlesen

                // Loop through all remaining tokens of the current line
                while (tok.hasMoreTokens()) { // nun folgen Kanten mit Gewichten
                    // Get value of next token and use value to get id of end vertex
                    t = tok.nextToken();
                    Vertex end = vertices.get(Integer.valueOf(t)); // Kante

                    // Get value of next token and use it as weight
                    t = tok.nextToken();
                    weight = Double.valueOf(t);

                    // Add new edge to the edges hash set
                    edges.add(new Edge(start, end, weight));
                }
            }
        }

        // Return a new graph containing all of the red edges of the lines list
        return new Graph(edges);
    }
}
