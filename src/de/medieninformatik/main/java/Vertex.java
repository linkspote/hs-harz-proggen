import java.util.Objects;

/**
 *
 */
public class Vertex implements Comparable<Vertex> { // Vertices sind nach Id geordnet
    // Declare class attributes
    private final int vertexId;
    private final int x; // Koordinaten nur zum Zeichnen, für Algorithmen nicht relevant
    private final int y; // gehen daher auch nicht in equals und hashCode ein.

    /**
     *
     * @param id
     * @param x
     * @param y
     */
    public Vertex(int id, int x, int y) {
        this.vertexId = id;
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return
     */
    public int getVertexId() {
        return this.vertexId;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return vertex.compareTo(this) == 0;
    }

    /**
     *
     * @return
     */
    @Override
    public final int hashCode() {
        return Objects.hash(vertexId);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Vertex o) {
        return this.vertexId - o.vertexId;
    }
}