package de.medieninformatik.cg;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to create a {@code KDTree} which stores {@code Point2D} objects (the double variant) and contains a method to
 * find the k nearest neighbours of a specified point.
 *
 * @author Kevin Kleiber
 * @version 1.0
 */
public class KDTree {

    /**
     * Used to store information about each element of the k-d-tree such as coordinates of a point and a left/right node
     * object.
     * <p></p>
     * Access modifiers are public because this class can only be accessed from the outer {@code KDTree} class.
     *
     */
    private class Node {
        // Declare necessary variables
        public Point2D coordinates;
        public Node left, right;

        /**
         * Constructs a new node containing the given information.
         *
         * @param p_coordinates
         */
        public Node(Point2D p_coordinates) {
            coordinates = p_coordinates;
        }
    }

    // Declare and initialise necessary variables
    private Node _root;
    private Map<Point2D, Double> _mNeighbours;
    private int _maxEntries;
    private Double _longestDistance = Double.MAX_VALUE;

    /**
     * Constructs the {@code KDTree} as a whole with all its leaf nodes, subtrees and a root node by utilizing the
     * given points list.
     *
     * @param p_lsPoints    points used to create the {@code KDTree}
     */
    public KDTree(List<Point2D> p_lsPoints) {
        _root = constructTree(p_lsPoints, 0);
    }

    /**
     * Constructs the whole k-d-tree from the root node of the tree and its subtrees to the leaf nodes.
     *
     * @param p_lsPoints    points list used for creating the k-d-tree/subtree
     * @param p_depth       depth of the current (sub-)tree
     * @return              a root node which contains a 2D point as well as a left and right (sub-)tree
     */
    private Node constructTree(List<Point2D> p_lsPoints, int p_depth) {
        // If list is empty nothing can be created
        if (p_lsPoints.size() == 0) return null;
        // If list contains last point its a leaf node
        if (p_lsPoints.size() == 1) return new Node(p_lsPoints.get(0));

        // Root of each (sub-)tree
        Node rootNode;
        // Get center point of points list and depth of next subtree
        int center = p_lsPoints.size() / 2;
        int nextDepth = p_depth + 1;

        // Determine splitting axis, x=0 and y=1
        int splitAxis = p_depth % 2;
        // If tree is split on x-axis sort points by values on x-axis
        if (splitAxis == 0) p_lsPoints.sort(Comparator.comparingDouble(Point2D::getX));
        // If tree is split on y-axis sort points by values on y-axis
        else p_lsPoints.sort(Comparator.comparingDouble(Point2D::getY));

        // Set current root node, left and right subtree
        rootNode = new Node(p_lsPoints.get(center));
        rootNode.left = constructTree(p_lsPoints.subList(0, center), nextDepth);
        rootNode.right = constructTree(p_lsPoints.subList(center + 1, p_lsPoints.size()), nextDepth);

        return rootNode;
    }

    /**
     * Prepares the k-d-tree by initialising some necessary variables for the knn search and returns a map containing
     * the k nearest neighbours with their distance to the pivot point.
     *
     * @param p_pivot       represents the point for which neighbours should be found
     * @param p_neighbours  the amount of neighbours to find, also sets the maximum size of the result map
     * @return              a map containing the neighbour point as key and the distance to the pivot point as value
     */
    public Map<Point2D, Double> findKNN(Point2D p_pivot, int p_neighbours) {
        // Init map for storing neighbours and set value for max entries of that map
        _mNeighbours = new HashMap<>();
        _maxEntries = p_neighbours;

        findKNN(_root, p_pivot, 0);

        return _mNeighbours;
    }

    /**
     * Finds the k nearest neighbours of the given pivot point.
     *
     * @param p_rootNode    represents the node where to start searching for neighbours
     * @param p_pivot       represents the point for which neighbours should be found
     * @param p_depth       depth of the current (sub-)tree
     */
    private void findKNN(Node p_rootNode, Point2D p_pivot, int p_depth) {
        // If current root node is null prevent further execution
        if (p_rootNode == null) return;

        // Determine splitting axis, x=0 and y=1
        int splitAxis = p_depth % 2;
        Node next, opposite;

        // If tree is split on x-axis compare x values and set nodes for advanced neighbour search
        if (splitAxis == 0) {
            boolean pivotXIsLower = Double.compare(p_pivot.getX(), p_rootNode.coordinates.getX()) < 0;
            next = (pivotXIsLower) ? p_rootNode.left : p_rootNode.right;
            opposite = (pivotXIsLower) ? p_rootNode.right : p_rootNode.left;
        }
        // If tree is split on y-axis compare y values and set nodes for advanced neighbour search
        else {
            boolean pivotYIsLower = Double.compare(p_pivot.getY(), p_rootNode.coordinates.getY()) < 0;
            next = (pivotYIsLower) ? p_rootNode.left : p_rootNode.right;
            opposite = (pivotYIsLower) ? p_rootNode.right : p_rootNode.left;
        }

        // Calculate distance between pivot and current root point, for neighbours it must be greater than 0.0
        double distance = p_pivot.distance(p_rootNode.coordinates);
        // If map size is lower than allowed size add new entries and update longest distance
        if (distance != 0.0 && _mNeighbours.size() < _maxEntries) {
            _mNeighbours.put(p_rootNode.coordinates, distance);
            _longestDistance = distance;
        }
        // If max map size is reached but there are still shorter distances remove longest distance and add new one
        else if (distance != 0.0 && Double.compare(distance, _longestDistance) < 0) {
            _mNeighbours.values().remove(_longestDistance);
            _mNeighbours.put(p_rootNode.coordinates, distance);
            _longestDistance = distance;
        }

        // Recursive call using the next subtree and a higher level
        findKNN(next, p_pivot, p_depth + 1);

        // Determine the current axisDistance
        double axisDistance = (splitAxis == 0) ? Math.abs(p_pivot.getX() - p_rootNode.coordinates.getX()) :
                Math.abs(p_pivot.getY() - p_rootNode.coordinates.getY());

        // If current axis distance is shorter chances are good that distance from opposite to pivot point is shorter
        if (_mNeighbours.size() < _maxEntries || Double.compare(axisDistance, _longestDistance) < 0)
            findKNN(opposite, p_pivot, p_depth + 1);
    }
}
