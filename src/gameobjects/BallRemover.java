package gameobjects;

import interfaces.Collidable;
import interfaces.HitListener;

/**
 * A class of HitListener.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter availableBalls;

    /**
     * The constructor method of gameobjects.BlockRemover.
     * @param gameLevel The gameLevel to join.
     * @param availableBalls Number of blocks in gameLevel.
     */
    public BallRemover(GameLevel gameLevel, Counter availableBalls) {
        this.gameLevel = gameLevel;
        this.availableBalls = availableBalls;
    }

    /**
     * Remove hit'd Collidable.
     * @param beingHit the Collidable that is being hit.
     * @param hitter the Ball that is hitting.
     */
    public void hitEvent(Collidable beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.gameLevel.updateBalls();
    }
}
