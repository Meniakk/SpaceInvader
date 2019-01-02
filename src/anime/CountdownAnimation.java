package anime;

import biuoop.DrawSurface;
import gameobjects.SpriteCollection;
import interfaces.Animation;
import java.awt.Color;


/**
 * A class of CountdownAnimation.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;
    private long timeToWait;
    private long nextTime;

    /**
     * The constructor method of CountdownAnimation.
     * @param numOfSeconds number of the seconds the animation takes.
     * @param countFrom the number to count down from.
     * @param gameScreen a list of sprites representing the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
        this.timeToWait = ((long) (((this.numOfSeconds) * 1000) / countFrom));
        this.nextTime = java.lang.System.currentTimeMillis() + this.timeToWait;

    }


    /**
     * Draws the objects.
     * @param d The draw surface to draw on.
     * @param dt The time passed.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        /* If the time has come countdown and set the next event. */
        if (java.lang.System.currentTimeMillis() >= this.nextTime) {
            this.nextTime += this.timeToWait; /* Setting the next time. */
            this.countFrom--;
        }

        /* Draws the screen. */
        this.gameScreen.drawAllOn(d);

        /* Draws the text (The countdown current number). */
        Color current = Color.black;
        for (int i = 0; i <= 20; i++) {

            if (current.equals(Color.black)) {
                current = Color.white;
            } else {
                current = Color.black;
            }

            d.setColor(current);
            d.drawText(345 + i, 400 + i, Integer.toString(this.countFrom), 200);

        }

        /* Checks if we should stop the animation */
        if (countFrom == 0) {
            this.running = false;
        }

    }

    /**
     * Notify when to stop.
     * @return boolean, True if we should stop, false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

}