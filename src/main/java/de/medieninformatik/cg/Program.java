package de.medieninformatik.cg;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This application generates 1024 random {@code Point2D} objects (by default), picks one of them (also random) and
 * then searches for its k neighbours.
 * <p></p>
 * When the user wants to change the amount of the randomly generated points he can use a command line parameter to do
 * so. The minimum value for each value is zero whereas the maximum value is one. The output is slightly formatted to
 * ensure readability of the results.
 *
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class Program {
    /**
     * Takes user input (if available), calculates k, creates random points, picks a random point and prints his k
     * nearest neighbours to the console.
     * <p></p>
     * The value of k is defined by sqrt([amount of points]). If the result is even (checked by modulo operation) it
     * will be increased by 1.
     *
     * @param args can be used to modify the amount of randomly generated {@code Point2D} objects
     */
    public static void main (String[] args) {
        // Amount of points to store in points list
        int amount = (args.length > 0) ? Integer.parseInt(args[0]) : 1024;

        // Common way to specify k is calc sqrt of amount of entries
        int k = (int) Math.sqrt(amount);
        // When k is even then add 1 to avoid confusion
        if (k % 2 == 0) k++;

        Random random = new Random();

        // Create list for storing points and fill it
        List<Point2D> lsPoints = new ArrayList<>();
        while (lsPoints.size() < amount) {
            Point2D p = new Point2D.Double(random.nextDouble(), random.nextDouble());

            // Avoid adding the same 2D point a 2nd time
            if (!lsPoints.contains(p)) lsPoints.add(p);
        }

        // Create new k-d-tree, select random pivot point and log it
        KDTree randomTree = new KDTree(lsPoints);
        Point2D randomPoint = lsPoints.get(random.nextInt(amount + 1));
        System.out.println("Selektierter Punkt: " + randomPoint + "\n");

        // Find k nearest neighbours of pivot point (excludes pivot point, distance = 0.0)
        Map<Point2D, Double> result = randomTree.findKNN(randomPoint, k);

        // Create counter and log every of the k neighbours to the console
        int i = 1;
        for (Point2D p: result.keySet()) {
            System.out.printf("%2d. Punkt: %s, Distanz: %s%n", i++, p, result.get(p));
        }
    }
}
