package anime;

import biuoop.DrawSurface;
import geometry.ColoredRectangle;
import geometry.Rectangle;
import interfaces.Animation;
import java.awt.Color;

/**
 * A class of PauseScreen.
 */
public class PauseScreen implements Animation {



    /**
     * The constructor method of PauseScreen.
     */
    public PauseScreen() {

    }

    /**
     * Draw one frame of the animation.
     * @param d The draw surface to draw on.
     * @param dt The time it takes to draw.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        /* Pause text. */
        d.drawText(10, d.getHeight() / 2,
                "paused -- press space to continue", 32);

        /* Pause base. */
        Rectangle rec = new Rectangle(560, 180, 200, 220);
        ColoredRectangle colRec = new ColoredRectangle(rec, Color.gray);
        colRec.drawOn(d);

        /* Pause marks. */
        rec = new Rectangle(600, 235, 40, 110);
        colRec = new ColoredRectangle(rec, Color.black);
        colRec.drawOn(d);

        /* Pause marks. */
        rec = new Rectangle(680, 235, 40, 110);
        colRec = new ColoredRectangle(rec, Color.black);
        colRec.drawOn(d);


    }

    /**
     * Returns if we should stop the animation.
     * @return if we should stop the animation.
     */
    public boolean shouldStop() {
        return false;
    }

}
