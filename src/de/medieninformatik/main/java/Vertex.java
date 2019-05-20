import java.util.Objects;

/**
 * The {@code Vertex} class is used to create the vertices for the Kruskal algorithm. In the GUI window they are
 * displayed as the lightblue circles containing their ids as text in green.
 */
public class Vertex implements Comparable<Vertex> { // Vertices sind nach Id geordnet
    // Declare class attributes
    private final int vertexId;
    private final int x; // Koordinaten nur zum Zeichnen, für Algorithmen nicht relevant
    private final int y; // gehen daher auch nicht in equals und hashCode ein.
    private double cost;
    private boolean burned;

    /**
     * Constructs a {@code Vertex} with the given attribute values for its id as well as the x and y position.
     *
     * @param id Represents the id of the {@code Vertex} to be constructed.
     * @param x  Represents the x position of the {@code Vertex} to be constructed.
     * @param y  Represents the y position of the {@code Vertex} to be constructed.
     */
    public Vertex(int id, int x, int y) {
        // Set values of class attributes according to parameter values
        this.vertexId = id;
        this.x = x;
        this.y = y;

        this.burned = false;
        this.cost = 0;
    }

    /**
     * Returns the id of a {@code Vertex}.
     *
     * @return The id of a {@code Vertex} as integer value.
     */
    public int getVertexId() {
        return this.vertexId;
    }

    /**
     * Returns the x position of a {@code Vertex} which is only needed for the graphical representation.
     *
     * @return The x position of a {@code Vertex} as integer value.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y position of a {@code Vertex} which is only needed for the graphical representation.
     *
     * @return The y position of a {@code Vertex} as integer value.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Checks if the given object equals the current one and returns true in that case. Otherwise false will be
     * returned.
     *
     * @param o Represents another {@code Vertex} which shall or not shall equal the current one.
     * @return True when the ids or objects are equal, otherwise false.
     */
    @Override
    public final boolean equals(Object o) {
        // Return true if given object is current object/instance
        if (this == o) return true;

        // Return false if given object is null or classes of objects are unequal
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;
        // Return true if id of this vertex equals id of casted vertex
        return vertex.compareTo(this) == 0;
    }

    /**
     * Generates a hash code for the current {@code Vertex} and returns it.
     *
     * @return The hash code of the current {@code Vertex} as integer value.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(vertexId);
    }

    /**
     * Compares the ids of the current {@code Vertex} with the id of the given one by subtracting the given from the
     * current one.
     *
     * @param o Represents another {@code Vertex}.
     * @return The result of the subtraction of both ids as integer value.
     */
    @Override
    public int compareTo(Vertex o) {
        return this.vertexId - o.vertexId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean getBurned() {
        return burned;
    }

    public void setBurned(boolean burned) {
        this.burned = burned;
    }
}