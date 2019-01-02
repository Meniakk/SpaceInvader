package gameobjects;

import interfaces.Collidable;
import interfaces.HitListener;

/**
 * A class of ScoreTrackingListener.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * The constructor method of the class.
     * @param scoreCounter the Counter for score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Updating the score, 100 pt' for a kill.
     * @param beingHit the Collidable.
     * @param hitter the ball.
     */
    public void hitEvent(Collidable beingHit, Ball hitter) {
        this.currentScore.increase(100);
    }
}
