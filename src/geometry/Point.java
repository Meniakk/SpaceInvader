package geometry;

/**
 * A class of Point.
 */
public class Point {

    private double x;
    private double y;

    /**
     * The constructor method of Point.
     * @param x The coordinate of the point in the x plane.
     * @param y The coordinate of the point in the x plane.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets a Point and returns the distance to.
     * @param other A different Point the measure the distance to.
     * @return The distance between the two points.
     */
    public double distance(Point other) {
        double distance = Math.hypot(this.x - other.x , this.y - other.y);
        return distance;
    }

    /**
     * Gets a Point and returns is equal to.
     * @param other A different Point the check if equal to.
     * @return True - if Points are equals, else - False.
     */
    public boolean equals(Point other) {
        if ((this.x == other.x) && (this.y == other.y)) {
            return true;
        }
        return false;
    }

    /**
     * @return The coordinate of the point in the x plane.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return The coordinate of the point in the y plane.
     */
    public double getY() {
        return this.y;
    }
}
