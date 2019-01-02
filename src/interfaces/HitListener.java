package interfaces;

import gameobjects.Ball;

/**
 * An interface of HitListener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the game-Ball that's doing the hitting.
     * @param beingHit the block.
     * @param hitter the ball.
     */
    void hitEvent(Collidable beingHit, Ball hitter);
}
