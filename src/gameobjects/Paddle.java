package gameobjects;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;

/**
 * A class of gameobjects.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private GameLevel gameLevel;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int speed;
    private int width;
    private static final int PADDLE_HEIGHT = 20;
    private static final int FRAME_MAX_X = 800;
    private static long nextShotTime;
    private static boolean firstShot = true;
    private static final int SHOOT_INTERVAL = 350;

    /**
     * A constructor for gameobjects.Paddle.
     * @param gui The gui of paddle.
     * @param speed The speed of the paddle.
     * @param width The width of the paddle.
     * @param gameLevel The game the paddle is in.
     */
    public Paddle(GUI gui, int speed, int width, GameLevel gameLevel) {
        this.keyboard = gui.getKeyboardSensor();
        DrawSurface d = gui.getDrawSurface();

        this.gameLevel = gameLevel;
        this.speed = speed;
        this.width = width;

        double x = (d.getWidth() - width) / 2;
        this.rectangle = new Rectangle(x, 580, width, PADDLE_HEIGHT);
    }

    /**
     * Update the gameobjects.Paddle Rectangle.
     * @param newRectangle The new Rectangle.
     */
    public void setRectangle(Rectangle newRectangle) {
        this.rectangle = newRectangle;
    }

    /**
     * Moves the puddle left unless it is touching the border.
     * @param dt The time passed.
     */
    public void moveLeft(double dt) {
        double actualSpeed = dt * speed;
        /* Move Left */
        double curX = this.rectangle.getUpperLeft().getX();
        double curY = this.rectangle.getUpperLeft().getY();
        if (curX - actualSpeed < 0) {
            this.rectangle.setUpperLeft(new Point(0, curY));
        } else {
            this.rectangle.setUpperLeft(new Point(curX - actualSpeed, curY));
        }
    }

    /**
     * Moves the puddle right unless it is touching the border.
     * @param dt The time passed.
     */
    public void moveRight(double dt) {
        double actualSpeed = dt * speed;
        double curX = this.rectangle.getUpperLeft().getX();
        double curY = this.rectangle.getUpperLeft().getY();
        if (curX + this.width + actualSpeed > FRAME_MAX_X) {
            this.rectangle.setUpperLeft(new Point(FRAME_MAX_X - this.width, curY));
        } else {
            this.rectangle.setUpperLeft(new Point(curX + actualSpeed, curY));
        }
    }

    /**
     * Shots a bullet.
     * @param dt the time passed.
     */
    public void shoot(double dt) {

        long time = java.lang.System.currentTimeMillis();

        if (firstShot) {
            int delta = (int) (0.5 * this.rectangle.getWidth());
            int x = (int) this.rectangle.getUpperLeft().getX() + delta;
            Point point = new Point(x, (int) this.rectangle.
                    getUpperLeft().getY() - 10);
            Ball attack = new Ball(point, true, gameLevel);
            attack.shoot();
            firstShot = false;
            nextShotTime = time + SHOOT_INTERVAL;
        } else {
            if (time >= nextShotTime) {
                int delta = (int) (0.5 * this.rectangle.getWidth());
                int x = (int) this.rectangle.getUpperLeft().getX() + delta;
                Point point = new Point(x, (int) this.rectangle.
                        getUpperLeft().getY() - 10);
                Ball attack = new Ball(point, true, gameLevel);
                attack.shoot();
                nextShotTime = time + SHOOT_INTERVAL;
            }
        }



    }

    /**
     * Checking if a key was pressed, if so move the paddle.
     * @param dt The time passed.
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            shoot(dt);
        }

    }

    /**
     * Draws the paddle on the DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d, Color.orange);
    }

    /**
     * Gets the paddle rectangle.
     * @return the paddle rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handle the ball hitting the paddle,
     * checks which side the ball will hit and returns
     * a new expected velocity for the ball.
     * @param collisionPoint The collision point.
     * @param currentVelocity The object current velocity.
     * @return The new expected velocity for the ball.
     * @param hitter the ball that hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity
            currentVelocity) {

        /* if the bullet was from enemy - die */
        if (!hitter.isFriendly()) {
            this.removeFromGame(gameLevel);
            gameLevel.setPlayerDead();
        }

        /* Returning the new Velocity. */
        return currentVelocity;
    }

    /**
     * Adds a paddle to the gameLevel.
     * @param gameLevel2Add the gameLevel to add the paddle to.
     */
    public void addToGame(GameLevel gameLevel2Add) {
        gameLevel2Add.addCollidable(this);
        gameLevel2Add.addSprite(this);
    }

    /**
     * Removes a paddle to the gameLevel.
     * @param gameLevel2Remove the gameLevel to remove the paddle from.
     */
    public void removeFromGame(GameLevel gameLevel2Remove) {
        gameLevel2Remove.removeCollidable(this);
        gameLevel2Remove.removeSprite(this);
    }

    /**
     * Tells if the object is friendly to the player.
     * @return boolean if the object is friendly to the player.
     */
    public boolean isFriend() {
        return true;
    }

}
