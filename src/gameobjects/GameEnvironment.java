package gameobjects;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * A class of GameEnvironment.
 */
public class GameEnvironment {

    private List<Collidable> collidableList;

    /**
     * The GameEnvironment constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * Returns the list of interfaces.Collidable objects.
     * @return List<Collidable> - the list of Collidable objects.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * Setting the collidableList member of GameEnvironment.
     * @param newCollidableList the new value of collidableList.
     */
    public void setCollidableList(List<Collidable> newCollidableList) {
        this.collidableList = newCollidableList;
    }

    /**
     * Add the given collidable to the environment.
     * @param c The collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Remove the given collidable to the environment.
     * @param c The collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null.
     * Else, return the information about the closest collision
     * that is going to occur.
     * @param trajectory the Ball movement line.
     * @return CollisionInfo - Details of the collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        /* Return null if GameEnvironment is empty. */
        if (this.collidableList.isEmpty()) {
            return null;
        }

        Collidable minCollidable = null;
        Point minInter = null;
        double minDistance = -1;

        /* perform the iteration on a copy of the list. */
        List<Collidable> copyList = new ArrayList<>(this.collidableList);
        for (Collidable col: copyList) {
            Rectangle rec = col.getCollisionRectangle();
            Point inter = trajectory.closestIntersectionToStartOfLine(rec);
            /* If we got an intersection point check its distance. */
            if (inter != null) {
                double distance = inter.distance(trajectory.start());
                /* If this intersection point is closer set it as min. */
                if ((minDistance > distance) || (minDistance == -1)) {
                    minDistance = distance;
                    minInter = inter;
                    minCollidable = col;
                }
            }
        }

        /* If there were no intersection points return null. */
        if (minCollidable == null) {
            return null;
        }
        return new CollisionInfo(minInter, minCollidable);
    }
}
