package anime;

import biuoop.DrawSurface;
import gameobjects.Counter;
import geometry.Rectangle;
import interfaces.Sprite;
import java.awt.Color;

/**
 * A class of anime.ScoreIndicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle scoreBar;

    /**
     * The constructor method of anime.ScoreIndicator.
     * @param scoreBar the rectangle to draw.
     * @param counter the score counter.
     */
    public ScoreIndicator(Rectangle scoreBar, Counter counter) {
        this.scoreBar = scoreBar;
        this.score = counter;
    }

    /**
     * Draw the sprite to the screen.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        scoreBar.drawOn(d, Color.RED);
        scoreBar.drawText(d, "Score: " + this.score.toString());
    }

    /**
     * Notify the sprite that time has passed.
     * @param dt The time passed.
     */
    public void timePassed(double dt) {

    }

}
