package anime;

import biuoop.DrawSurface;
import interfaces.Animation;
import saves.HighScoresTable;
import saves.ScoreInfo;


/**
 * A class of HighScoresAnimation.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scoresTable;

    /**
     * The constructor method of HighScoresAnimation.
     * @param scores The highscores table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scoresTable = scores;
    }

    /**
     * Draw one frame of the animation.
     * @param d The draw surface to draw on.
     * @param dt the time passed
     */
    public void doOneFrame(DrawSurface d, double dt) {

        /* High-score text. */
        d.drawText(250, 100, "High-Scores", 50);
        d.fillRectangle(250, 105, 280, 3);

        /* High scores. */
        int i = 60;
        for (ScoreInfo score : scoresTable.getHighScores()) {
            d.drawText(200, 100 + i, score.toString(), 35);
            i += 60;
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
