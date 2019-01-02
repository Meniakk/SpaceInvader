package anime;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;


/**
 * A calss of anime.LivesIndicator.
 */
public class LevelIndicator implements Sprite {

    private String levelName;

    /**
     * The constructor method of LevelIndicator.
     * @param levelName the name of the level.
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Draw the sprite to the screen.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {

        /* Setting the color. */
        d.setColor(Color.white);

        d.drawText(572, 13, "Level: " + this.levelName + "", 10);

    }

    /**
     * Notify the sprite that time has passed.
     * @param dt the time passed.
     */
    public void timePassed(double dt) {

    }

    /**
     * Sets the level name.
     * @param newName the new level name.
     */
    public void setLevelName(String newName) {
        this.levelName = newName;
    }

}
