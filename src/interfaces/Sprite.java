package interfaces;

import biuoop.DrawSurface;

/**
 * An interface of Sprite.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d the DrawSurface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     * @param dt The time passed.
     */
    void timePassed(double dt);
}
