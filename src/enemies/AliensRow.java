package enemies;

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of Aliens Row.
 */
public class AliensRow implements Sprite {

    private List<Alien> aliensRow;
    private int numberOfRow;
    private static final int MOVE_VERT = 50;
    private static final int ALIEN_WIDTH = 40;
    private static final int ALIEN_HEIGHT = 30;
    private static final int ALIEN_SPACE = 10;

    /**
     * The constructor.
     * @param aliensRow The list of aliens.
     * @param numberOfRow The number of this Row.
     */
    public AliensRow(List<Alien> aliensRow, int numberOfRow) {
        this.aliensRow = aliensRow;
        this.numberOfRow = numberOfRow;
    }

    /**
     * Adds an alien to the row.
     * @param alien the alien to add
     */
    public void add(Alien alien) {
        this.aliensRow.add(alien);
    }

    /**
     * removing an alien from the row.
     * @param alien the alien that was killed.
     */
    public void killAlien(Alien alien) {
        this.aliensRow.remove(alien);
    }

    /**
     * Find the lowest alien and tell him to shoot.
     */
    public void shoot() {
        getBottomAlien().shoot();
    }

    /**
     * Move the aliens right/left.
     * @param moveDir The move direction.
     * @param moveDis the move distance.
     */
    public void moveHorizontal(double moveDir, double moveDis) {
        for (Alien alien : this.aliensRow) {
            double x = alien.getCollisionRectangle().getUpperLeft().getX();
            x = x + (moveDir * moveDis);
            double y = alien.getCollisionRectangle().getUpperLeft().getY();
            double width = alien.getCollisionRectangle().getWidth();
            double height = alien.getCollisionRectangle().getHeight();
            alien.setRectangle(new Rectangle(x, y, width, height));
        }
    }

    /**
     * Move the aliens Down.
     */
    public void moveVertical() {
        try {
            for (Alien alien : this.aliensRow) {
                double x = alien.getCollisionRectangle().getUpperLeft().getX();
                double y = alien.getCollisionRectangle().getUpperLeft().getY();
                y += MOVE_VERT;
                double width = alien.getCollisionRectangle().getWidth();
                double height = alien.getCollisionRectangle().getHeight();
                alien.setRectangle(new Rectangle(x, y, width, height));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Draw the sprite to the screen.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        List<Alien> copyList = new ArrayList<>(this.aliensRow);
        for (Alien alien : copyList) {
            if (alien.isAlive()) {
                alien.drawOn(d);
            } else {
                this.aliensRow.remove(alien);
            }
        }
    }

    /**
     * Notify the sprite that time has passed.
     * @param dt The time passed.
     */
    public void timePassed(double dt) {

    }

    /**
     * Returns the alien Row.
     * @return the alien row.
     */
    public List<Alien> getAliensRow() {
        return aliensRow;
    }

    /**
     * Resets the aliens positions.
     * @param startX the starting X of the aliens.
     * @param startY the starting Y of the aliens.
     */
    public void resetPos(int startX, int startY) {

        int mult = this.numberOfRow;

        int currentRowX = startX + (mult * ALIEN_SPACE) + (mult * ALIEN_WIDTH);
        int currentRowY = startY;

        for (Alien alien : this.aliensRow) {
            alien.resetPos(currentRowX, currentRowY);
        }



    }

    /**
     * Returns the bottom Alien in the row.
     * @return the bottom Alien in the row.
     */
    public Alien getBottomAlien() {
        return  this.aliensRow.get(this.aliensRow.size() - 1);
    }

}
