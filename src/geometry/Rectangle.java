package geometry;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of rectangle.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * A constructor method of Rectangle.
     * @param upperLeft the start Point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        /*Point downTight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);*/
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * A constructor method of Rectangle.
     * @param x the x of the start Point of the rectangle.
     * @param y the y of the start Point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line - the line to check intersection with.
     * @return ArrayList - A list of intersection points.
     */
    public  java.util.List<Point> intersectionPoints(Line line) {
        /* Creating the list. */
        List<Point> list = new ArrayList<>();

        /**/
        boolean isInList = false;

        /* Converting the rectangle to four lines.  */
        Point[] points = rectangleToPoints(this);
        Line[] lines = pointsToLines(points);

        /*
        * Foreach line check if intersect;
        * if so, check if the Point is already in the list,
        * if so, add the point to the list.
        * else continue.
        */
        for (Line recLine: lines) {
            if (recLine.isIntersecting(line)) {
                Point inter = recLine.intersectionWith(line);
                for (Point point: list) {
                    if (point.equals(inter)) {
                        isInList = true;
                    }
                }
                if (!isInList) {
                    list.add(inter);
                }
            }
            isInList = false;
        }
        return list;
    }

    /**
     * Convert a rectangle to four Points objects.
     * @param rec The rectangle to convert.
     * @return Point[] - a list of four points.
     */
    public Point[] rectangleToPoints(Rectangle rec) {
        /* Creating the array. */
        Point[] points = new Point[4];

        /* Creating the points. */
        Point p00 = rec.getUpperLeft();
        Point p01 = new Point(p00.getX() + rec.getWidth(), p00.getY());
        Point p02 = new Point(p00.getX(), p00.getY() + rec.getHeight());
        Point p03 = new Point(p01.getX(), p02.getY());

        /* Add the points to the array. */
        points[0] = p00;
        points[1] = p01;
        points[2] = p02;
        points[3] = p03;

        /* Returns the array. */
        return points;
    }

    /**
     * Convert a rectangle point's to four Lines objects.
     * @param points - An array of four Points.
     * @return Line[] - An array of four Lines.
     */
    public Line[] pointsToLines(Point[] points) {
        /* Creating the array. */
        Line[] lines = new Line[4];

        /* Creating the lines and adding to the array. */
        lines[0] = new Line(points[0], points[1]);
        lines[1] = new Line(points[0], points[2]);
        lines[2] = new Line(points[2], points[3]);
        lines[3] = new Line(points[1], points[3]);

        /* Returns the array. */
        return lines;
    }

    /**
     * Returns the upperLeft Point of the rectangle.
     * @return Point - the upperLeft Point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Draws the rectangle.
     * @param d The DrawSurface to draw on.
     * @param color The color of the rectangle.
     */
    public void drawOn(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) this.getWidth(),
                (int) this.getHeight());
    }

    /**
     * Draws a frame of the rectangle.
     * @param d The DrawSurface to draw on.
     */
    public void drawOnFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) this.getWidth(),
                (int) this.getHeight());
    }

    /**
     * Sets the UpperLeft member.
     * @param newUpperLeft the new UpperLeft.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * Draws text on the middle of the rectangle.
     * @param d The DrawSurface to draw on.
     * @param string The string to write.
     */
    public void drawText(DrawSurface d, String string) {
        /* Getting the middle of the rectangle */
        int x = (int) this.getUpperLeft().getX() + ((int) this.width / 2);
        int y = (int) this.getUpperLeft().getY() + ((int) this.height / 2);

        /* Draws the text. */
        d.setColor(Color.white);
        d.drawText(x, y, string, 10);
    }

}
