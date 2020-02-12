package de.medieninformatik.cg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code Main} class contains the main() method which represents the main execution point of each program.
 *
 * <p>ATTENTION: This program ist just an example!
 *
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Main {
    /**
     * This method is the main execution point of each program.
     *
     * @param args Represents a String array which contains parameters to configure the program execution.
     */
    public static void main (String[] args) {
        // Amount of entries for kdTree
        int amount = (args.length > 0) ? Integer.parseInt(args[0]) : 1024;

        // Common way to specify k is calc sqrt of n
        int k = (int) Math.sqrt(amount);
        // When k is even then add 1 to avoid confusion
        if (k % 2 == 0) k++;

        List<kdTree> lsTree = new ArrayList<>();
        lsTree.add(new kdTree(-1, 3));
        lsTree.add(new kdTree(-1, -1));
        lsTree.add(new kdTree(1, 1));
        lsTree.add(new kdTree(2, .5));
        lsTree.add(new kdTree(2, -1));
        lsTree.add(new kdTree(3, 3));
        lsTree.add(new kdTree(4, 2));
        lsTree.add(new kdTree(4, -.5));

        // Do something else here
        kdTree closest = findNN(new kdTree(4, 2), lsTree);
        System.out.println(closest.toString() + ", Distance: " + distance(4, closest.getX(), 2, closest.getY()));
        int[] indices = findKNN(3, new kdTree(4, 2), lsTree);
        for (int i = 0; i < indices.length; i++) {
            closest = lsTree.get(indices[i]);
            System.out.println(closest.toString() + ", Distance: " + distance(4, closest.getX(), 2, closest.getY()));
        }
    }

    private static kdTree findNN(kdTree p_origin, List<kdTree> p_lsNeighbours) {
        kdTree closest = p_lsNeighbours.get(0);
        double shortest = distance(p_origin.getX(), closest.getX(), p_origin.getY(), closest.getY());

        for (int i = 0; i < p_lsNeighbours.size(); i++) {
            double dist = distance(p_origin.getX(), p_lsNeighbours.get(i).getX(), p_origin.getY(), p_lsNeighbours.get(i).getY());

            if (dist < shortest && dist != 0.0d) {
                shortest = dist;
                closest = p_lsNeighbours.get(i);
            }
        }

        return closest;
    }

    private static int[] findKNN(int p_k, kdTree p_origin, List<kdTree> p_lsNeighbours) {
        int[] neighbourIndices = new int[p_k];
        double[] neighbourDistances = new double[p_k];
        Arrays.fill(neighbourDistances, Double.MAX_VALUE);

        for (int i = 0; i < p_lsNeighbours.size(); i++) {
            double dist = distance(p_origin.getX(), p_lsNeighbours.get(i).getX(), p_origin.getY(), p_lsNeighbours.get(i).getY());
            if (dist == 0.0) continue;
            double maxDist = Double.MIN_VALUE;
            int maxIndex = 0;
            System.out.println("[" + i + "] Dist: " + dist);

            for (int j = 0; j < p_k; j++) {
                if (maxDist < neighbourDistances[j]) {
                    maxDist = neighbourDistances[j];
                    maxIndex = j;
                }
            }

            if (neighbourDistances[maxIndex] > dist) {
                neighbourDistances[maxIndex] = dist;
                neighbourIndices[maxIndex] = i;
            }
        }

        return neighbourIndices;
    }

    // Calculates euclidean distance between two points
    private static double distance(double p_x1, double p_x2, double p_y1, double p_y2) {
        return Math.sqrt(Math.pow(p_x1 - p_x2, 2) + Math.pow(p_y1 - p_y2, 2));
    }
}
