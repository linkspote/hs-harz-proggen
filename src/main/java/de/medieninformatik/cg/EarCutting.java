package de.medieninformatik.cg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EarCutting} class defines how the application reacts to user input by mouse and triggers the specified
 * methods.
 * <p></p>
 * It uses the ear cutting algorithm to triangulate simple polygons and is capable of doing so in clockwise and
 * counter-clockwise order. It includes some very basic error handling and resets the application if necessary to let
 * the user continue using it.
 *
 * @author Kevin Kleiber (m26675)
 * @version 1.0
 */
public class EarCutting extends JPanel {
    // Lists for storing polygon points and triangles
    private List<Point> lsPoints = new ArrayList<>();
    private List<Polygon> lsTriangles = new ArrayList<>();

    // Check variable for triangulation process
    private boolean bTriangulated = false;

    // Stroke for drawing the polygon and its triangles
    private Stroke stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    /**
     * Constructs a new object of the ear cutting component, sets a window size and registers mouse listeners for user
     * input.
     */
    public EarCutting() {
        setPreferredSize(new Dimension(600, 480));

        MouseAdapter adapter = new MouseAdapter() {
            /**
             * Controls what happens when the left, right or middle mouse button got pressed.
             * <p></p>
             * Using the left mouse button the user can create a new point for the polygon to triangulate. The right
             * mouse button is used to trigger the triangulation process while the middle mouse button resets the input.
             * <p></p>
             * The user is not able to create multiple polygons at once. Each time he left clicks after triggering the
             * triangulation the former input will be cleared.
             *
             * @param e a {@code MouseEvent} that occurred
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (bTriangulated) clear();
                    lsPoints.add(new Point(e.getX(), e.getY()));
                }
                else if (!bTriangulated && SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Triangulierung wird gestartet...");

                    bTriangulated = true;
                    triangulatePoly();

                    System.out.println("Triangulierung beendet...\n");
                }
                else if (SwingUtilities.isMiddleMouseButton(e))
                    clear();

                repaint();
            }
        };

        addMouseListener(adapter);
    }

    /**
     * Draws the polygon and its triangles to the panel by calling the corresponding methods.
     *
     * @param g the {@code Graphics} object to work with
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!bTriangulated) drawPolygon((Graphics2D) g);
        else drawTriangles((Graphics2D) g);
    }

    /**
     * Draws the entered polygon onto the panel.
     * <p></p>
     * Starting at 3 entered points a NOT triangulated polygon becomes visible and reshaped with each additional point.
     *
     * @param g2d the {@code Graphics2D} object to work with
     */
    private void drawPolygon(Graphics2D g2d) {
        g2d.setStroke(stroke);

        for (int i = 0; i < lsPoints.size(); i++) {
            Point p1 = lsPoints.get(i % lsPoints.size());
            Point p2 = lsPoints.get((i + 1) % lsPoints.size());

            g2d.setColor(new Color(25, 25, 25));
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    /**
     * Draws the triangles after the triangulation onto the panel.
     * <p></p>
     * For better visibility the triangles are filled with a slightly transparent color.
     *
     * @param g2d the {@code Graphics2D} object to work with
     */
    private void drawTriangles(Graphics2D g2d) {
        g2d.setStroke(stroke);

        for (Polygon triangle : lsTriangles) {
            g2d.setColor(new Color(65, 198, 133, 64));
            g2d.fillPolygon(triangle);

            g2d.setColor(new Color(65, 122, 106));
            g2d.drawPolygon(triangle);
        }
    }

    /**
     * Triangulates the entered polygon using an ear cutting algorithm for simple polygons.
     * <p></p>
     * The algorithm works clockwise and counter-clockwise and stops when there are too many iterations necessary. If
     * that's the case then the user probably entered an too complex polygon like an 8 which intersects itself.
     */
    private void triangulatePoly() {
        boolean clockwise = isClockwise(), bFailed = false;
        int i = 0;
        int iterationLimit = lsPoints.size() * 100;
        List<String> lsOutput = new ArrayList<>();

        while (lsPoints.size() > 2 && i < iterationLimit) {
            // Modulo operation to prevent OutOfBoundsException and to enable more index constellations, e.g. [5, 2, 3]
            Point p1 = lsPoints.get((i) % lsPoints.size());
            Point p2 = lsPoints.get((i + 1) % lsPoints.size());
            Point p3 = lsPoints.get((i + 2) % lsPoints.size());

            // Cross product of the three points: x1 * y2 - y1 * x2
            double cross = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);

            Polygon triangle = new Polygon();
            triangle.addPoint(p1.x, p1.y);
            triangle.addPoint(p2.x, p2.y);
            triangle.addPoint(p3.x, p3.y);

            // Determine whether polygon was entered clockwise or not and is valid
            if (((clockwise && cross >= 0) || (!clockwise && cross <= 0)) && validTriangle(triangle, p1, p2, p3)) {
                lsPoints.remove(p2);
                lsTriangles.add(triangle);

                // Log the triangles collection
                lsOutput.add("Dreieck hinzugefügt: p0(x = " + p1.x + ", y = " + p1.y + "), "
                    + "p1(x = " + p2.x + ", y = " + p2.y + "), p2(x = " + p3.x + ", y = " + p3.y + ")");
            }
            else
                i++;
        }

        if (lsTriangles.size() < 1) {
            bFailed = true;
            JOptionPane.showMessageDialog(null, "Für die Triangulierung müssen mindestens 3 "
                + "Punkte vorhanden sein.");
            clear();
        }
        else if (i == iterationLimit) {
            bFailed = true;
            JOptionPane.showMessageDialog(null,
                "Sie haben das Iterationslimit erreicht. Es wurde gesetzt, um eine endlose Ausführung des "
                + "Programms frühzeitig zu stoppen. Möglicherweise ist das angegebene Polygon zu komplex.");
            clear();
        }

        // Print triangles "collection" to console when execution was successful
        if (!bFailed) lsOutput.forEach(System.out::println);
    }

    /**
     * Checks if the current triangle is valid.
     * <p></p>
     * Traverses through the points list, checks if the three given points are not the same and if any other point is
     * inside of the given triangle. If this happens to be the case the triangle is invalid.
     *
     * @param p_plTriangle  the polygon triangle to check
     * @param p_pt1         one of three triangle points
     * @param p_pt2         one of three triangle points
     * @param p_pt3         one of three triangle points
     * @return              {@code true} if triangle is valid, {@code false} if not
     */
    private boolean validTriangle(Polygon p_plTriangle, Point p_pt1, Point p_pt2, Point p_pt3) {
        for (Point p : lsPoints)
            if (p != p_pt1 && p != p_pt2 && p != p_pt3 && p_plTriangle.contains(p))
                return false;

        return true;
    }

    /**
     * Determines whether the entered polygon edges are in clockwise or counter-clockwise order.
     * <p></p>
     * It calculates the signed area of the entered polygon. The formula is created for Cartesian coordinate systems but
     * this application has an inverted y-axis therefore the return statement was inverted too for correct behaviour.
     * <p></p>
     * More information: <a href="https://stackoverflow.com/questions/1165647/how-to-determine-if-a-list-of-polygon-points-are-in-clockwise-order">
     * How to determine if a list of polygon points are in clockwise order?</a>
     *
     * @return  {@code true} if the edges are ordered clockwise, {@code false} if counter-clockwise
     */
    private boolean isClockwise() {
        int iSum = 0;

        for (int i = 0; i < lsPoints.size(); i++) {
            Point pt1 = lsPoints.get(i);
            Point pt2 = lsPoints.get((i + 1) % lsPoints.size());

            iSum += (pt2.x - pt1.x) * (pt2.y + pt1.y);
        }

        return iSum <= 0;
    }

    /**
     * Clears the points as well as the triangles list and resets the boolean for the triangulation check.
     */
    private void clear() {
        lsPoints.clear();
        lsTriangles.clear();
        bTriangulated = false;
    }
}
