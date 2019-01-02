package geometry;

import java.util.List;

/**
 * A class of Line.
 */
public class Line {

    private Point start;
    private Point end;

    /**
     * One of the constructor method of Line.
     * @param start The starting point of the segment
     * @param end The ending point of the segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * One of the constructor method of Line.
     * @param x1 The X of the starting point of the segment
     * @param y1 The Y of the starting point of the segment
     * @param x2 The X of the ending point of the segment
     * @param y2 The Y of the ending point of the segment
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point newStart = new Point(x1, y1);
        Point newEnd = new Point(x2, y2);
        this.start = newStart;
        this.end = newEnd;
    }

    /**
     * Returns the length of the line.
     * @return Int that represent the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculate the middle point of the line.
     * @return Point - the middle of the line.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the starting Point of the line.
     * @return Point - the starting Point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending Point of the line.
     * @return Point - the ending Point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Check if two lines intersect with each other.
     * @param other The line to check with.
     * @return boolean - True if the lines intersect,
     * else false.
     */
    public boolean isIntersecting(Line other) {

        /* The slopes of the lines */
        double slopeLine1;
        double slopeLine2;

        /* Max and Min of this line */
        double maxPointLine1;
        double minPointLine1;
        /* Max and Min of other line */
        double maxPointLine2;
        double minPointLine2;

        /* Checking if both lines are vertical */
        if ((this.start.getX() == this.end.getX())
                && (other.start.getX() == other.end.getX())) {

            /* They can meet only of they have the same X value */
            if (this.start.getX() == other.start.getX()) {
                return xorSegmentsPointEquals(this, other);
            } else {
                return false;
            }
        }

        /* Checking if one (only) of the lines is vertical */
        if ((this.start.getX() == this.end.getX())
                ^ (other.start.getX() == other.end.getX())) {
            if (this.start.getX() == this.end.getX()) {
                /* If it is this line get slope and func */
                slopeLine2 = getSlope(other);
                double func2 = other.start.getY()
                        - other.start.getX() * slopeLine2;
                double x0 = this.start.getX();
                double y0 = slopeLine2 * this.start.getX() + func2;
                /* Checking if the joint point is in the segments */
                return isInSegment(x0, y0, this, other);
            } else {
                /* If it is other line get slope and func */
                slopeLine1 = getSlope(this);
                double func1 = this.start.getY()
                        - (this.start.getX() * slopeLine1);
                double x0 = other.start().getX();
                /* this.start.getY() - func1 * other.start.getX(); */
                double y0 = (slopeLine1 * other.start.getX()) + func1;
                /* Checking if the joint point is in the segments */
                return isInSegment(x0, y0, this, other);
            }
        }

        /* Calculating the slopes */
        slopeLine1 = (this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX());
        slopeLine2 = (other.start.getY() - other.end.getY())
                / (other.start.getX() - other.end.getX());

        /* if slopes are equals check if they have joint points */
        if (slopeLine1 == slopeLine2) {
            return xorSegmentsPointEquals(this, other);
        }

        /* Calculating the lines functions */
        double func1 = this.start.getY() - (this.start.getX() * slopeLine1);
        double func2 = other.start.getY() - (other.start.getX() * slopeLine2);

        /* Calculating the joint point (all un-parallel have a joint point) */
        double x0 = -(func1 - func2) / (slopeLine1 - slopeLine2);
        /* Calculating the Y of the joint point */
        double y0 = slopeLine1 * x0 + func1;

        return isInSegment(x0, y0, this, other);
    }

    /**
     * Calculate the intersection Point of two lines with each other.
     * @param other The line to check with.
     * @return Point - if point exist returns the point,
     * else null.
     */
    public Point intersectionWith(Line other) {
        /* if does not meet return null */
        if (!this.isIntersecting(other)) {
            return null;
        }
        /* The slopes of the lines */
        double slopeLine1;
        double slopeLine2;

        /* Checking if both lines are vertical */
        if (this.start.getX() == this.end.getX()
                && other.start.getX() == other.end.getX()) {
            if (this.start.equals(other.start)
                    || this.start.equals(other.end)) {
                return this.start;
            } else {
                return this.end;
            }
        }

        /* Checking if one (only) of the lines is vertical */
        if (this.start.getX() == this.end.getX()
                ^ other.start.getX() == other.end.getX()) {
            if (this.start.getX() == this.end.getX()) {
                /* If it is this line */
                slopeLine2 = getSlope(other);
                double func2 = other.start.getY()
                        - other.start.getX() * slopeLine2;
                double y0 = slopeLine2 * this.start.getX() + func2;
                return new Point(this.start.getX(), y0);
            } else {
                /* If it is other line */
                slopeLine1 = getSlope(this);
                double func1 = this.start.getY()
                        - this.start.getX() * slopeLine1;
                double y0 = slopeLine1 * other.start.getX() + func1;
                return new Point(other.start.getX(), y0);
            }
        }

        /* Calculating the slopes */
        slopeLine1 = getSlope(this);
        slopeLine2 = getSlope(other);

        /* if slopes are equals check which end is the joint point */
        if (slopeLine1 == slopeLine2) {
            if ((this.start.equals(other.start))
                    || (this.start.equals(other.end))) {
                return this.start;
            } else {
                return this.end;
            }
        }

        /* If those are two normal lines check for joint point */
        /* Calculating the lines functions */
        double func1 = this.start.getY() - this.start.getX() * slopeLine1;
        double func2 = other.start.getY() - other.start.getX() * slopeLine2;

        /* Calculating the joint point (all un-parallel have a joint point) */
        double x0 = -(func1 - func2) / (slopeLine1 - slopeLine2);

        /* Calculating the Y of the joint point */
        double y0 = slopeLine1 * x0 + func1;

        /* Returning the joint point*/
        return new Point(x0, y0);
        // return new Point((int) Math.round(x0), (int) Math.round(y0));
    }

    /**
     * Checks if ONE of the point in a line equals
     * one of the point in the second line.
     * @param a First line.
     * @param b Second line.
     * @return boolean - Returns true if
     * ONE of the point in a line equals
     * one of the point in the second line,
     * else false.
     */
    private boolean xorSegmentsPointEquals(Line a, Line b) {

        /* old check. */
         /*if ((a.start.equals(b.start) ||/^ a.start.equals(b.end))
                ^ (a.end.equals(b.start) ||/^ a.end.equals(b.end))) {
            return true;
         }
         //return false;*/

        /*
         * Checking if the line segment limit are equal
         * if so - the segments has a joint point, return true
         * else - the lines has infinity of joint points or none, in any
         * case return false
         */
        return ((a.start.getY() == b.start().getY()
                ^ a.start.getY() == b.end().getY())
                ^ (a.end.getY() == b.start().getY()
                ^ a.end.getY() == b.end().getY()));
    }

    /**
     * Calculate the slope of a line.
     * @param a the line to calculate.
     * @return double - the slope of the line.
     */
    private double getSlope(Line a) {
        return (a.start.getY() - a.end.getY())
                / (a.start.getX() - a.end.getX());
    }

    /**
     * Check if a point is in the lines.
     * @param x the value of the point in the X plane.
     * @param y the value of the point in the Y plane.
     * @param line1 first line.
     * @param line2 second line.
     * @return boolean - true if point is in segments,
     * else false.
     */
    private boolean isInSegment(double x, double y, Line line1, Line line2) {

        /* Max and Min of this line X. */
        double maxPointLine1X;
        double minPointLine1X;
        /* Max and Min of other line X.*/
        double maxPointLine2X;
        double minPointLine2X;

        /* Max and Min of this line Y. */
        double maxPointLine1Y;
        double minPointLine1Y;
        /* Max and Min of other line Y. */
        double maxPointLine2Y;
        double minPointLine2Y;


        /* Getting Min and Max of lines */
        maxPointLine1X =
                (Math.max(line1.start.getX(), line1.end.getX()));
        minPointLine1X =
                (Math.min(line1.start.getX(), line1.end.getX()));
        maxPointLine1Y =
                (Math.max(line1.start.getY(), line1.end.getY()));
        minPointLine1Y =
                (Math.min(line1.start.getY(), line1.end.getY()));

        maxPointLine2Y =
                (Math.max(line2.start.getY(), line2.end.getY()));
        minPointLine2Y =
                (Math.min(line2.start.getY(), line2.end.getY()));
        maxPointLine2X =
                (Math.max(line2.start.getX(), line2.end.getX()));
        minPointLine2X =
                (Math.min(line2.start.getX(), line2.end.getX()));

        /* Checking if the joint point is in the segments */
        return (maxPointLine1X
                + 0.0001 >= x && minPointLine1X
                - 0.0001 <= x && maxPointLine2X
                + 0.0001 >= x && minPointLine2X
                - 0.0001 <= x && maxPointLine1Y
                + 0.0001 >= y && minPointLine1Y
                - 0.0001 <= y && maxPointLine2Y
                + 0.0001 >= y && minPointLine2Y
                - 0.0001 <= y);
        ///////////////////
        /* old check */
        /* Getting Min and Max of lines */
        /*boolean isXInSeg = false;
        boolean isYInSeg = false;
        maxPointLine1X =
                Math.round(Math.max(line1.start.getX(), line1.end.getX()));
        minPointLine1X =
                Math.round(Math.min(line1.start.getX(), line1.end.getX()));
        maxPointLine2X =
                Math.round(Math.max(line2.start.getX(), line2.end.getX()));
        minPointLine2X =
                Math.round(Math.min(line2.start.getX(), line2.end.getX()));*/
        /* Getting Min and Max of lines */
        /*maxPointLine1Y =
                Math.round(Math.max(line1.start.getY(), line1.end.getY()));
        minPointLine1Y =
                Math.round(Math.min(line1.start.getY(), line1.end.getY()));
        maxPointLine2Y =
                Math.round(Math.max(line2.start.getY(), line2.end.getY()));
        minPointLine2Y =
                Math.round(Math.min(line2.start.getY(), line2.end.getY()));
        isXInSeg = ((minPointLine1X <= x) && (x <= maxPointLine1X)
                && (minPointLine2X <= x) && (x <= maxPointLine2X));
        isYInSeg = ((minPointLine1Y <= y) && (y <= maxPointLine1Y)
                && (minPointLine2Y <= y) && (y <= maxPointLine2Y));
        return  ((isXInSeg) && (isYInSeg));*/
        ///////////////////

    }

    /**
     * Checks which collision is closet to the ball and returns it.
     * @param rect The rectangle we hit.
     * @return Point- the closest point to the start of the line will hit.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        /* Getting the intersection points list. */
        //java.util.List<?> lists = rect.intersectionPoints(this);
        List<Point> list = rect.intersectionPoints(this);


        /* If array is empty return null. */
        if (list.isEmpty()) {
            return null;
        }

        double minDistance = list.get(0).distance(rect.getUpperLeft());
        Point minPoint = list.get(0);

        /*
        * Foreach intersection point check its distance from line
        * starting point and find the minimum distance.
         */
        for (Point point: list) {
            if (minDistance > point.distance(this.start())) {
                minDistance = point.distance(this.start());
                minPoint = point;
            }
        }

        /* Returns the intersection Point with the least distance. */
        return minPoint;
    }
}
