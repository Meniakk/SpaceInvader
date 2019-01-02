package gameobjects;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of gameobjects.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners = new ArrayList<>();
    private Rectangle rectangle;
    private Color backgroundColor;

    /**
     * A default constructor for extending.
     */
    public Block() { }

    /**
     * Returns the Alien's Block.
     * @return the Alien's Block.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * The constructor method of gameobjects.Block.
     * @param rec The Rectangle object.
     * @param backgroundColor The Rectangle object Color.
     */
    public Block(Rectangle rec, Color backgroundColor) {
        this.rectangle = rec;
        this.backgroundColor = backgroundColor;
    }

    /**
     * Draws the block.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        /* Draws the filled rectangle */
        Rectangle rec = this.getCollisionRectangle();
        rec.drawOn(d, this.backgroundColor);
    }

    /**
     * Sets the current block rectangle.
     * @param newRectangle the current block rectangle.
     */
    public void setRectangle(Rectangle newRectangle) {
        this.rectangle = newRectangle;
    }

    /**
     * Returns the Rectangle object of gameobjects.Block object.
     * @return Rectangle - the Rectangle object of gameobjects.Block object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit.
     * @param collisionPoint the collision Point.
     * @param currentVelocity the given velocity.
     * @param hitter the ball that hit us.
     * @return gameobjects.Velocity - the new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity
            currentVelocity) {
        /*
        * notifiers all of the registered HitListener objects by calling
        * their hitEvent method.
        */
        this.notifyHit(hitter);

        /* Returning the new Velocity. */
        return currentVelocity;
    }

    /**
     * Notify the block that time passed.
     * @param dt The time passed.
     */
    public void timePassed(double dt) {

    }

    /**
     * Adding the block to the correct objects.
     * @param gameLevel the gameobjects.GameLevel to join.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Removing the block from the game.
     * @param gameLevel the game to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Adding a hit listener to the block.
     * @param h1 the hit listener to add.
     */
    public void addHitListener(HitListener h1) {
        hitListeners.add(h1);
    }

    /**
     * Removing a hit listener to the block.
     * @param h1 the hit listener to remove.
     */
    public void removeHitListener(HitListener h1) {
        hitListeners.remove(h1);
    }

    /**
     * Notifying all the members we had a hit.
     * @param hitter the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners
                = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Tells if the object is friendly to the player.
     * @return boolean if the object is friendly to the player.
     */
    public boolean isFriend() {
        return true;
    }
}
