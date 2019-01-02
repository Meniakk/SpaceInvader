package interfaces;

import gameobjects.Ball;
import gameobjects.GameLevel;
import gameobjects.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * An interface of interfaces.Collidable.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint The collision point.
     * @param currentVelocity The object current velocity.
     * @param hitter the ball that hitting us.
     * @return the new velocity expected
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Removing the Collidable from the game.
     * @param gameLevel the game to remove the Collidable from.
     */
    void removeFromGame(GameLevel gameLevel);

    /**
     * Tells if the object is friendly to the player.
     * @return boolean if the object is friendly to the player.
     */
    boolean isFriend();
}
