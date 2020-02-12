package de.medieninformatik.cg;

public class kdTree {

    private double x, y;
    private boolean left, right, leaf;

    public kdTree(double p_x, double p_y) {
        this.x = p_x;
        this.y = p_y;
        left = false;
        right = false;
        leaf = false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "kdTree{" +
                "x=" + x +
                ", y=" + y +
                ", left=" + left +
                ", right=" + right +
                ", leaf=" + leaf +
                '}';
    }
}
