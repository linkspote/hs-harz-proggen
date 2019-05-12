import java.io.IOException;
import java.util.*;

/**
 *
 */
public class Graph {
    private HashSet<Vertex> vertices;
    private HashSet<Edge> edges;

    /**
     *
     */
    public Graph() {
        this.vertices = new HashSet<>();
        this.edges    = new HashSet<>();
    }

    /**
     *
     * @param v
     */
    public Graph(Vertex v) {
        this();
        this.vertices.add(v);
    }

    /**
     *
     * @param edges
     */
    public Graph(Set<Edge> edges) {
        this();
        this.edges.addAll(edges);
        this.edges.forEach( e -> { // Da HashSet, wird wiederholtes Einfuegen desselben Vertex ignoriert
            vertices.add(e.getStartVertex());
            vertices.add(e.getEndVertex());
        });
    }

    /**
     *
     * @return
     */
    public int numberOfVertices() {
        return vertices.size();
    }

    /**
     *
     * @param v
     * @return
     */
    public boolean contains(Vertex v) {
        return vertices.contains(v);
    }

    /**
     *
     * @return
     */
    public Set<Vertex> getVertices() {
        return vertices;
    }

    /**
     *
     * @return
     */
    public Set<Edge> getEdges() {
        return edges;
    }

    /**
     *
     * @param lines
     * @return
     * @throws IOException
     */
    public static Graph read(List<String> lines) throws IOException {
        // Zuerst: Lies Vertices aus den Zeilen ein
        int n = lines.size();
        ArrayList<Vertex> vertices = new ArrayList<>(n);
        int i = 0;
        for(String s : lines) {
            if(s.startsWith("#")) continue; // Kommentarzeilen uberlesen.
            StringTokenizer tok = new StringTokenizer(s);
            if(tok.countTokens() > 1) {
                vertices.add(new Vertex(i++,
                        Integer.valueOf(tok.nextToken()),
                        Integer.valueOf(tok.nextToken())
                ));
            }
        }

        // Finde die gewichteten Kanten zu jedem Vertex
        Set<Edge> edges = new HashSet<>();
        i = 0;
        double weight;
        for(String s : lines) {
            if(s.startsWith("#")) continue; // Kommentarzeilen uberlesen.
            StringTokenizer tok = new StringTokenizer(s);
            if(tok.countTokens() %2 == 1) throw new IOException("ungerade Anzahl von Elementen!");
            String t;
            if(tok.countTokens() > 1) {
                Vertex start = vertices.get(i++);
                t = tok.nextToken(); // x ueberlesen
                t = tok.nextToken(); // y ueberlesen
                while (tok.hasMoreTokens()) { // nun folgen Kanten mit Gewichten
                    t = tok.nextToken();
                    Vertex end = vertices.get(Integer.valueOf(t)); // Kante
                    t = tok.nextToken();
                    weight = Double.valueOf(t);
                    edges.add(new Edge(start, end, weight));
                }
            }
        }
        return new Graph(edges);
    }
}
