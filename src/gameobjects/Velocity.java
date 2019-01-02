package gameobjects;

import geometry.Point;

/**
 * A class of gameobjects.Velocity.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * The constructor method of Ball.
     * @param dx how much to move on the X plane.
     * @param dy how much to move on the Y plane.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Moves a point according to it's velocity.
     * @param p the point to move.
     * @return Point - a new point with the new coordinates.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Moves a point according to it's velocity.
     * @param p the point to move.
     * @param dt The time passed.
     * @return Point - a new point with the new coordinates.
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + (this.dx * dt),
                p.getY() + (this.dy * dt));
    }

    /**
     * Gets the X velocity.
     * @return double - the velocity on the X plane.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the Y velocity.
     * @return double - the velocity on the Y plane.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Creates a velocity from angle and speed.
     * @param angle the angle of the new velocity.
     * @param speed the speed of the new velocity.
     * @return gameobjects.Velocity - a new velocity created
     * according to the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        /* Sets the new velocity */
        double dx =  speed * Math.cos(Math.toRadians(angle));
        double dy =  speed * Math.sin(Math.toRadians(angle));

        /* Returning the new velocity. */
        return new Velocity(dx, dy);
    }

    /**
     * Returns the ball speed.
     * @return double - the ball speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }
}
