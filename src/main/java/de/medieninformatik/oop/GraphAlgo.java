package de.medieninformatik.oop;

import java.util.*;
import java.util.stream.Collectors;

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

            // Check is end vertex is not part of start vertex tree
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
            }
        }
        while (!edges.isEmpty());
        // return the new graph build with the Kruskal algorithm
        return (Graph) trees.values().stream().limit(1).toArray()[0];
    }

    public static Graph dijkstra(Graph g, Vertex startVertex) {
        // set containing all vertices of the graph
        HashSet<Vertex> vertices = new HashSet<>(g.numberOfVertices());
        vertices.addAll(g.getVertices());
        // this set will be used to add all new edges
        HashSet<Edge> newEdges = new HashSet<>();

        // initialise currentVertex with startVertex
        Vertex currentVertex = startVertex;

        // as long as there are still unburned vertices left
        while(g.hasUnburnedVertices()) {
            // makiere den Vertex als verbrannt
            currentVertex.setBurned(true);
            // hole des Kanten des Vertex
            ArrayList<Edge> vertexEdges = new ArrayList<>();
            for (Edge edge: g.getEdges()) {
                if(edge.contains(currentVertex)) {
                    vertexEdges.add(edge);
                }
            }

            // for every edge connected to the current vertex
            for (Edge edge: vertexEdges) {
                // go to the neighbor vertex
                Vertex neighborVertex = edge.getOtherVertex(currentVertex);
                // add an edge, if it does not have costs yet
                if(neighborVertex.getCost() == 0) {
                    neighborVertex.setCost(currentVertex.getCost()+edge.getWeight());
                    newEdges.add(edge);
                // add a new edge, if this edge is more cost efficient
                } else if(neighborVertex.getCost() > currentVertex.getCost()+edge.getWeight()) {
                    neighborVertex.setCost(currentVertex.getCost()+edge.getWeight());
                    // remove previoulsy added edges, if necessary
                    for (Edge newEdge: g.getEdges()) {
                        if(newEdge.contains(neighborVertex)) {
                            newEdges.remove(newEdge);
                        }
                    }
                    newEdges.add(edge);
                }
            }

            // search for the next vertex
            double smallestCost = 999;
            for (Vertex v: vertices) {
                // if the vertex is not burned and has lowest cost, select it as next vertex
                if(!v.getBurned() && (v.getCost() < smallestCost) && (Double.compare(v.getCost(), 0) != 0)) {
                    // update lowest cost
                    smallestCost = v.getCost();
                    currentVertex = v;
                }
            }
        }

        return new Graph(newEdges);
    }
}