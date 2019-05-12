import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 */
public class GraphAlgo {

    /**
     *
     * @param g
     * @return
     */
    public static Graph kruskal(Graph g) {
        HashMap<Vertex, Graph> trees = new HashMap<>(g.numberOfVertices());
        g.getVertices().forEach((vertex) -> trees.put(vertex, new Graph(vertex)));

        PriorityQueue<Edge> edges = new PriorityQueue<Edge>(Comparator.comparing(Edge::getWeight));
        edges.addAll(g.getEdges());


        do {
            Edge edge = edges.poll();
            Vertex startVertex = edge.getStartVertex();
            Vertex endVertex = edge.getEndVertex();

            Graph treeStartVertex = trees.get(startVertex);

            if (!treeStartVertex.contains(endVertex)) {
                Graph treeEndVertex = trees.get(endVertex);
                treeStartVertex.getVertices().addAll(treeEndVertex.getVertices());
                treeStartVertex.getEdges().addAll(treeEndVertex.getEdges());
                treeStartVertex.getEdges().add(edge);
                treeEndVertex.getVertices().forEach((vertex) -> trees.replace(vertex, treeStartVertex));
                // TODO:  Kleineren Baum nutzen
            }
        }
        while (!edges.isEmpty());

        return (Graph) trees.values().stream().limit(1).toArray()[0]; // TODO: Optimieren
        // TODO: Implementieren Sie den Algorithmus von Kruskal.
    }
}