package geometry;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class of colorful rectangle.
 */
public class ColoredRectangle {

    private Rectangle rectangle;
    private Color color;

    /**
     * A constructor method of Rectangle.
     * @param rectangle The rectangle of the object.
     * @param color The color of the object.
     */
    public ColoredRectangle(Rectangle rectangle, Color color) {

        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Draws the rectangle.
     * @param d The DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Setting the color of the rectangle.
     * @param newColor the new color.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Returns the color of the rectangle.
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }
}
