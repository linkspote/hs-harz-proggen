package de.medieninformatik.cg;

import com.sun.javafx.geom.Vec2d;
import com.sun.javafx.geom.Vec2f;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EarCutting extends JPanel {

    private List<Point> lsPoints = new ArrayList<>();
    private List<Polygon> lsTriangles = new ArrayList<>();

    private Stroke stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private Color color = new Color(255, 0, 0, 64);

    public EarCutting() {
        setPreferredSize(new Dimension(600, 480));

        MouseAdapter ma = new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (SwingUtilities.isLeftMouseButton(e)){
                    lsPoints.add(new Point(e.getX(), e.getY())); // TODO: 2nd Polygon not available, clear before drawing new Polygon
                    System.out.println("Click");}
                else if (SwingUtilities.isRightMouseButton(e))
                    triangulatePoly();
                else if (SwingUtilities.isMiddleMouseButton(e))
                    lsTriangles.clear(); // TODO: Clear anything
                repaint();
            }
        };

        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPolygon((Graphics2D) g);
        drawTriangles((Graphics2D) g); // TODO: Only when necessary
    }

    /**
     *
     * @param g2d
     */
    private void drawPolygon(Graphics2D g2d) {
        g2d.setStroke(stroke);

        for (int i = 0; i < lsPoints.size(); i++) {
            Point p1 = lsPoints.get(i % lsPoints.size());
            Point p2 = lsPoints.get((i + 1) % lsPoints.size());
            g2d.setColor(Color.BLUE);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    /**
     *
     * @param g2d
     */
    private void drawTriangles(Graphics2D g2d) {
        g2d.setStroke(stroke);

        for (Polygon triangle : lsTriangles) {
            g2d.setColor(color);
            g2d.fillPolygon(triangle);
            g2d.setColor(Color.RED);
            g2d.drawPolygon(triangle);
        }
    }

    // TODO: Add check for convex/concave
    private void triangulatePoly() {
        boolean clockwise = isClockwise();
        int i = 0;
        int iterationLimit = lsPoints.size() * 100;

        while (lsPoints.size() > 2 && i < iterationLimit) {
            Point p1 = lsPoints.get((i) % lsPoints.size());
            Point p2 = lsPoints.get((i + 1) % lsPoints.size());
            Point p3 = lsPoints.get((i + 2) % lsPoints.size());

            // Cross product: x1 * y2 - y1 * x2
            double cross = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);

            Polygon triangle = new Polygon();
            triangle.addPoint(p1.x, p1.y);
            triangle.addPoint(p2.x, p2.y);
            triangle.addPoint(p3.x, p3.y);

            System.out.println(cross);
            if (((clockwise && cross >= 0) || (!clockwise && cross <= 0))
                    && validTriangle(triangle, p1, p2, p3, lsPoints)) {
                lsPoints.remove(p2);
                lsTriangles.add(triangle);
            }
            else
                i++;
        }

        if (lsPoints.size() < 3)
            lsPoints.clear();
        else if (i == iterationLimit) JOptionPane.showMessageDialog(null, "Sie haben vermutlich das Iterationslimit erreicht. Es wurde gesetzt, um Endlosschleifen zu vermeiden und diese im Problemfall möglichst frühzeitig zu stoppen.");
    }

    // TODO: Check if really necessary, as only three points are added to poly and should be in the points list
    private boolean validTriangle(Polygon poly, Point p1, Point p2, Point p3, List<Point> p_lsPoints) {
        for (Point p : p_lsPoints)
            if (p != p1 && p != p2 && p != p3 && poly.contains(p))
                return false;

        return true;
    }

    /**
     * Determines whether the entered polygon edges are in clockwise or counter-clockwise order.
     * <p></p>
     * It calculates the signed area of the entered polygon. The formula is created for Cartesian coordinate systems but
     * this application has an inverted y-axis therefore the return statement was inverted too for correct behaviour.
     * <p></p>
     * More information: <a href="https://stackoverflow.com/questions/1165647/how-to-determine-if-a-list-of-polygon-points-are-in-clockwise-order">How to determine if a list of polygon points are in clockwise order?</a>
     *
     * @return  Returns <code>true</code> if the edges are ordered clockwise
     */
    private boolean isClockwise() {
        int sum = 0;

        for (int i = 0; i < lsPoints.size(); i++) {
            Point p1 = lsPoints.get(i);
            Point p2 = lsPoints.get((i + 1) % lsPoints.size());

            sum += (p2.x - p1.x) * (p2.y + p1.y);
        }

        return sum <= 0;
    }
}
