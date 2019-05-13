import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *  The <code>GraphAlgo</code> class only contains one method which is used to apply the Kruskal Algorithm to a given
 *  <code>Graph</code>.
 *
 * @author Kai Gutsmann (m26667)
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 *
 */
public class GraphAlgo {

    /**
     *  This method is used to apply the Kruskal Algorithm to a given <code>Graph</code>. It uses a <code>HashMap</code>
     *  to store each <code>Vertex</code> and its corresponding <code>Graph</code>. A <code>PriorityQueue</code> is used
     *  to get the Edges in ascending order of weight and then decide wether or not the Graphs of start and end
     *  <code>Vertex</code> need to be combined or if the <code>Edge</code> can be deleted.
     * @param g Initial graph to use the algorithm on
     * @return minimal spanning tree of initial graph
     */
    public static Graph kruskal(Graph g) {
        // Create hash map with vertex and a corresponding trivial tree
        HashMap<Vertex, Graph> trees = new HashMap<>(g.numberOfVertices());
        g.getVertices().forEach((vertex) -> trees.put(vertex, new Graph(vertex)));
        // Store all edges in a queue according to their weight
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>(Comparator.comparing(Edge::getWeight));
        edges.addAll(g.getEdges());
        // For every edge, decide if trees of start and end vertex need to be combined
        do {
            // Get start and end vertex from an edge in the queue
            Edge edge = edges.poll();
            Vertex startVertex = edge.getStartVertex();
            Vertex endVertex = edge.getEndVertex();
            // Get tree with start vertex
            Graph treeStartVertex = trees.get(startVertex);

            if (!treeStartVertex.contains(endVertex)) {
                // Get tree containing end vertex
                Graph treeEndVertex = trees.get(endVertex);
                // Combine both trees
                treeStartVertex.getVertices().addAll(treeEndVertex.getVertices());
                treeStartVertex.getEdges().addAll(treeEndVertex.getEdges());
                // Add the edge to combine both trees
                treeStartVertex.getEdges().add(edge);
                // Update hash map with new graphs for each vertex of the end tree
                treeEndVertex.getVertices().forEach((vertex) -> trees.replace(vertex, treeStartVertex));
                // TODO:  Kleineren Wald nutzen
            }
        }
        while (!edges.isEmpty());
        // return the new graph build with the Kruskal algorithm
        return (Graph) trees.values().stream().limit(1).toArray()[0];
    }
}