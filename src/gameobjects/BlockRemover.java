package gameobjects;

import interfaces.Collidable;
import interfaces.HitListener;

/**
 * A class of BlockRemover.
 */
public class BlockRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * The constructor method of gameobjects.BlockRemover.
     * @param gameLevel The gameLevel to join.
     * @param removedBlocks Number of blocks in gameLevel.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * kill/spare and update game objects according to the hit.
     * @param beingHit the block that is being hit.
     * @param hitter the Ball that is hitting.
     */
    public void hitEvent(Collidable beingHit, Ball hitter) {

        /*
        * If the bullet is 'friendly' - kill everything it touches but don't
        * count it as an Enemy destroyed.
        * else - If the beingHit is enemy, do nothing.
        *   else - Kill the player
         */
        if (hitter.isFriendly()) {
            if (beingHit.isFriend()) {
                beingHit.removeFromGame(this.gameLevel);
            } else {
                beingHit.removeFromGame(this.gameLevel);
                /* Updating the game a block was destroyed */
                this.gameLevel.updateBlocks();
            }
        } else {
            if (beingHit.isFriend()) {
                beingHit.removeFromGame(this.gameLevel);
            }
        }

        /* Removing the shot */
        hitter.removeFromGame(this.gameLevel);

    }
}
