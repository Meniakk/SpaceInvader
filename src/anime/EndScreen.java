package anime;

import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;

/**
 * A class of EndScreen.
 */
public class EndScreen implements Animation {

    private int score;
    private boolean won;

    /**
     * The constructor method of EndScreen.
     * @param score the final score of the game.
     * @param won if the player won true, else false.
     */
    public EndScreen(boolean won, int score) {
        this.won = won;
        this.score = score;
    }

    /**
     * Draw one frame of the animation.
     * @param d The draw surface to draw on.
     * @param dt The time it takes to draw.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        /* Background */
        d.setColor(Color.orange);
        d.fillRectangle(0, 0, 800, 600);

        /* Text */
        d.setColor(Color.BLACK);


        if (won) { /* If the player won */
            String text = "You Win! Your score is " + this.score;
            d.drawText(10, d.getHeight() / 2, text, 32);

        } else { /* if lost */
            String text = "Game Over. Your score is " + this.score;
            d.drawText(10, d.getHeight() / 2, text, 32);

        }
    }

    /**
     * Returns if we should stop the animation.
     * @return if we should stop the animation.
     */
    public boolean shouldStop() {
        return false;
    }

}
