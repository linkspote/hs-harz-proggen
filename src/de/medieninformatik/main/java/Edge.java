import java.util.Objects;

/**
 * The {@code Edge} class is used to create the edges for the Kruskal algorithm which connect two vertices of the
 * {@code Vertex} class. In the GUI windows they are displayed as black or red strokes and are titled with their weight.
 */
public class Edge {
    // Declare class attributes
    private final Vertex startVertex;
    private final Vertex endVertex;
    private final double weight;

    /**
     * Constructs an {@code Edge} using the given attribute values. The two given vertices represent the start and end
     * {@code Vertex} of the edge. The start vertex is always the lower one.
     *
     * @param v1 Represents one of two given vertices which can be both a start or end {@code Vertex} of the new
     *           {@code Edge}.
     * @param v2 Represents one of two given vertices which can be both a start or end {@code Vertex} of the new
     *           {@code Edge}.
     * @param w  Represents the weight of the {@code Edge} to be constructed.
     */
    public Edge(Vertex v1, Vertex v2, double w) {
        // Compare the ids of the two vertices
        final int cmp = v1.compareTo(v2); // startVertex ist immer kleiner als endVertex (ungerichteter Graph)
        // When cmp is lower than zero v1 is start vertex otherwise v2 is start vertex
        this.startVertex = cmp < 0 ? v1 : v2;
        // When cmp is lower than zero v2 is end vertex otherwise v1 is end vertex
        this.endVertex = cmp < 0 ? v2 : v1;
        // Set weight of edge according to given parameter value
        this.weight = w;
    }

    /**
     * Returns the start {@code Vertex} of the current {@code Edge}.
     *
     * @return The start {@code Vertex} of the current {@code Edge} as vertex object.
     */
    public Vertex getStartVertex() {
        return startVertex;
    }

    /**
     * Returns the end {@code Vertex} of the current {@code Edge}.
     *
     * @return The end {@code Vertex} of the current {@code Edge} as vertex object.
     */
    public Vertex getEndVertex() {
        return endVertex;
    }

    /**
     * Returns the weight of the current {@code Edge}.
     *
     * @return The weight of the current {@code Edge} as double value.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Checks if the given object equals the current one and returns true in that case. Otherwise false will be
     * returned.
     *
     * @param o Represents another {@code Edge} which shall or not shall equal the current one.
     * @return True when the vertices and weights or objects are equal, otherwise false.
     */
    @Override
    public final boolean equals(Object o) {
        // Return true if given object is current object/instance
        if (this == o) return true;

        // Return false if given object is not an edge
        if (!(o instanceof Edge)) return false;

        final Edge edge = (Edge) o;
        // Return true if the vertices and both edges weights are equal
        return startVertex.compareTo(edge.startVertex) == 0 &&
                endVertex.compareTo(edge.endVertex) == 0 &&
                Double.compare(edge.weight, weight) == 0;
    }

    /**
     * Generates a hash code for the current {@code Edge} based on all three class attributes and returns it.
     *
     * @return The hash code of the current {@code Edge} as integer value.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(startVertex, endVertex, weight);
    }
}