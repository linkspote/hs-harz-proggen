import java.util.Objects;

/**
 *
 */
public class Edge {
    // Declare class attributes
    private final Vertex startVertex;
    private final Vertex endVertex;
    private final double weight;

    /**
     *
     * @param v1
     * @param v2
     * @param w
     */
    public Edge(Vertex v1, Vertex v2, double w) {
        final int cmp = v1.compareTo(v2); // startVertex ist immer kleiner als endVertex (ungerichteter Graph)
        this.startVertex = cmp < 0 ? v1 : v2;
        this.endVertex   = cmp < 0 ? v2 : v1;
        this.weight      = w;
    }

    /**
     *
     * @return
     */
    public Vertex getStartVertex() {
        return startVertex;
    }

    /**
     *
     * @return
     */
    public Vertex getEndVertex() {
        return endVertex;
    }

    /**
     *
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        final Edge edge = (Edge) o;
        return startVertex.compareTo(edge.startVertex) == 0 &&
                endVertex.compareTo(edge.endVertex) == 0 &&
                Double.compare(edge.weight, weight) == 0;
    }

    /**
     *
     * @return
     */
    @Override
    public final int hashCode() {
        return Objects.hash(startVertex, endVertex, weight);
    }
}