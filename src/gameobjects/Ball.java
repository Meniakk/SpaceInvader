package gameobjects;

import biuoop.DrawSurface;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import geometry.Line;
import geometry.Point;

/**
 * A class of gameobjects.Ball.
 */
public class Ball implements Sprite, HitNotifier {
    private List<HitListener> hitListeners = new ArrayList<>();
    private Point point;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private boolean friendly;
    private GameLevel gameLevel;
    /* Bullet's properties */
    private static final int BULLET_SPEED = 300;
    private static final int BULLET_SIZE = 2;

    /**
     * A constructor for Ball.
     * @param point the ball pos.
     * @param friendly the ball agenda.
     * @param gameLevel The game we are in.
     */
    public Ball(Point point, boolean friendly, GameLevel gameLevel) {
        this.point = point;
        this.friendly = friendly;
        this.gameLevel = gameLevel;
    }

    /**
     * Creates a new bullet and fire.
     */
    public void shoot() {
        /* Sets bullet properties according to who is shooting */
        if (this.friendly) {
            this.setColor(Color.YELLOW);
            this.setVelocity(0, -BULLET_SPEED);
        } else {
            this.setColor(Color.red);
            this.setVelocity(0, BULLET_SPEED);
        }
        this.r = BULLET_SIZE;
        this.gameEnvironment = gameLevel.getEnvironment();
        this.addToGame(gameLevel);

    }

    /**
     * One of the constructor method of gameobjects.Ball.
     * @param point the center of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point point, int r, Color color) {
        this.point = point;
        this.r = r;
        this.color = color;
    }

    /**
     * One of the constructor method of gameobjects.Ball.
     * @param x the value on the X plane of the center of the ball.
     * @param y the value on the Y plane of the center of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * One of the constructor method of gameobjects.Ball.
     * @param x the value on the X plane of the center of the ball.
     * @param y the value on the Y plane of the center of the ball.
     * @param r the radius of the ball.
     */
    public Ball(int x, int y, int r) {
        this.point = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Gets the value on the X plane of the center of the ball.
     * @return int - the value on the X plane of the center of the ball.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * Gets the value on the Y plane of the center of the ball.
     * @return int - the value on the Y plane of the center of the ball.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Gets tha ball radius.
     * @return int - the ball radius.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the ball velocity.
     * @return gameobjects.Velocity - the ball velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Gets the ball color.
     * @return Color - the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the ball color.
     * @param newColor the new color.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Sets the velocity of a ball.
     * @param v The new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of a ball.
     * @param dx how much to move on the X plane.
     * @param dy how much to move on the Y plane.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Setting the game gameEnvironment member of gameobjects.Ball.
     * @param newGameEnvironment the new gameEnvironment value.
     */
    public void setGameEnvironment(GameEnvironment newGameEnvironment) {
        this.gameEnvironment = newGameEnvironment;
    }

    /**
     * Paint a ball on a draw surface.
     * @param surface the draw surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(this.getX(), this.getY(), this.getSize(), this.getSize());
    }

    /**
     * Moves the ball according to his velocity and point.
     * @param dt The time passed.
     */
    public void moveOneStep(double dt) {

        /* The current position of the ball. */
        Point currentPoint = this.point;

        /* The next position of the ball. */
        Point nextPoint = this.getVelocity().applyToPoint(this.point, dt);

        /* The trajectory of the ball. */
        Line trajectory = new Line(currentPoint, nextPoint);

        /* Getting the collision info. */
        CollisionInfo collisionInfo = this.gameEnvironment
                .getClosestCollision(trajectory);

        /* If no collision in sight, move the ball. */
        if (collisionInfo != null) {
            /* Get the closest collision point. */
            Point collision = collisionInfo.collisionPoint();

            /* Move the ball to "almost" hit point. */
            // ?

            /* Notify the hit object that a collision occurred. */
            Velocity newV = collisionInfo.collisionObject().
                    hit(this, collision, this.velocity);

            /* Update the velocity to the new velocity. */
            this.setVelocity(newV);

            /* Moving the ball */
            this.point = this.getVelocity().applyToPoint(this.point, dt);

        } else {
            /* Move the ball to the end of the trajectory. */
            this.point = this.getVelocity().applyToPoint(this.point, dt);
        }
    }

    /**
     * Moves the ball one step.
     * @param dt The time passed.
     */
    public void timePassed(double dt) {
        if ((this.point.getX() < 0)
                || (this.point.getX() + BULLET_SIZE  > 800)
                || (this.point.getY() < 0)
                || (this.point.getY() + BULLET_SIZE > 600)) {
            this.removeFromGame(gameLevel);
        } else {
            this.moveOneStep(dt);
        }
    }

    /**
     * Adding the ball to the correct objects.
     * @param gameLevel2Add2 the gameobjects.GameLevel to join.
     */
    public void addToGame(GameLevel gameLevel2Add2) {
        gameLevel2Add2.addBullet(this);
        gameLevel2Add2.addSprite(this);
    }

    /**
     * Remove the ball from the game.
     * @param g the game to remove from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeBullet(this);
        g.removeSprite(this);
    }

    /**
     * Adding a interfaces.HitListener to a ball.
     * @param h1 The interfaces.HitListener to add.
     */
    public void addHitListener(HitListener h1) {
        hitListeners.add(h1);
    }

    /**
     * Removing a interfaces.HitListener to a ball.
     * @param h1 The interfaces.HitListener to remove.
     */
    public void removeHitListener(HitListener h1) {
        hitListeners.remove(h1);
    }

    /**
     * Notify the HitListeners of the hit.
     */
    private void notifyHit() {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners
                = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(null, this);
        }
    }

    /**
     * Returns if the Ball ws shot from an enemy or friend.
     * @return if the Ball ws shot from an enemy or friend.
     */
    public boolean isFriendly() {
        return friendly;
    }
}
