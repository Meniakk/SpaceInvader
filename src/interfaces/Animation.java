package interfaces;

import biuoop.DrawSurface;

/**
 * An interface of Animation.
 */
public interface Animation {

    /**
     * Draws the objects.
     * @param d The draw surface to draw on.
     * @param dt The time passed.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Notify when to stop.
     * @return boolean, True if we should stop, false otherwise.
     */
    boolean shouldStop();
}
