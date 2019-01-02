package anime;

import biuoop.DrawSurface;
import gameobjects.Counter;
import geometry.Point;
import interfaces.Sprite;
import java.awt.Color;

/**
 * A calss of anime.LivesIndicator.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * The constructor method of anime.LivesIndicator.
     * @param lives the counter of lives.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * Draw the sprite to the screen.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        /* The point to write the lives to. */
        Point point = new Point(38, 10);

        /* The amount of lives left. */
        int livesLeft = this.lives.getValue();

        /* Setting the color. */
        d.setColor(Color.white);

        d.drawText(1, 13, "Lives:", 10);

        /* Writes the amount of lives on the draw surface. */
        for (int i = livesLeft; i > 0; i--) {
            d.fillCircle((int) point.getX(), (int) point.getY(), 5);
            point = new Point((int) point.getX() + 12, (int) point.getY());
        }
    }

    /**
     * Notify the sprite that time has passed.
     * @param dt The time passed.
     */
    public void timePassed(double dt) {

    }

}
