package interfaces;

import enemies.AlienCol;

/**
 * An interface of a game level.
 */
public interface LevelInformation {

    /**
     * The paddle speed.
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * The paddle width.
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     * @return the level that will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level.
     * @return a list of Blocks that make up this level.
     */
    AlienCol blocks();

    /**
     * Number of blocks that should be removed before
     * the level is considered to be "cleared".
     * @return the number of blocks.
     */
    int numberOfBlocksToRemove();

}
