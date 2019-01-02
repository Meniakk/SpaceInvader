package enemies;

import biuoop.DrawSurface;
import gameobjects.GameLevel;
import interfaces.Sprite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class of a col. of akliens.
 */
public class AlienCol implements Sprite {


    private List<AliensRow> aliensColList = new ArrayList<>();
    private GameLevel gameLevel;
    private static long nextShot = java.lang.System.currentTimeMillis();
    private static final long SHOT_INTERVAL = 500;
    private static double moveDis = 1;
    private static double moveDir = 1;
    private static final double SHIELDS_START_Y = 500;


    /**
     * Sets the move distance.
     * @param newMoveDis the new  move distance.
     */
    public void setMoveDis(double newMoveDis) {
        AlienCol.moveDis = newMoveDis;
    }

    /**
     * Sets the move direction.
     * @param newMoveDir the new move direction.
     */
    public void setMoveDir(double newMoveDir) {
        AlienCol.moveDir = newMoveDir;
    }

    /**
     * Returns the list of rows.
     * @return the list of rows.
     */
    public List<AliensRow> getAliensColList() {
        return aliensColList;
    }

    /**
     * Draw the sprite to the screen.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        for (AliensRow aliensRow : this.aliensColList) {
            aliensRow.drawOn(d);
        }
    }

    /**
     * Notify the sprite that time has passed.
     * @param dt The time passed.
     */
    public void timePassed(double dt) {

        /* Check if any row is empty */
        List<AliensRow> copyList = new ArrayList<>(this.aliensColList);
        for (AliensRow aliensRow : copyList) {
            if (aliensRow.getAliensRow().isEmpty()) {
                remove(aliensRow);
            }
        }

        /* Check if an Alien got to the shields */
        if (checkIfAlienWon()) {
            this.aliensColList.get(0).getAliensRow().get(0).alienWon();
        }


        /* If all the aliens are dead we won! */
        if (this.aliensColList.isEmpty()) {
            return;
        }

        /*
        * if 0.5 seconds has passed since last shot,
        * fire from a random row.
        */
        long time = java.lang.System.currentTimeMillis();
        if (time >= nextShot) {
            nextShot = java.lang.System.currentTimeMillis() + SHOT_INTERVAL;

            Random rand = new Random();
            int n = rand.nextInt(this.aliensColList.size());
            try {
                aliensColList.get(n).shoot();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        /* Checking how to move */
        for (AliensRow aliensRow : this.aliensColList) {
            double currentX = aliensRow.getAliensRow().get(0)
                    .getCollisionRectangle().getUpperLeft().getX();
            double width = aliensRow.getAliensRow().get(0)
                    .getCollisionRectangle().getWidth();
            double actualMove = moveDis * moveDir;
            if ((currentX + width +  actualMove >= 800)
                    || (currentX + actualMove <= 0)) {
                /* moving aliens right/left */
                moveDis *= 1.1;
                moveDir *= -1;
                /* Moving aliens down */
                moveRowsVertical();
            }
        }
        /* Moving aliens to the side */
        moveRowsHorizontal();

    }

    /**
     * Moving each row downwards.
     */
    private void moveRowsVertical() {
        for (AliensRow aliensRow : aliensColList) {
            aliensRow.moveVertical();
        }
    }

    /**
     * Moving each row to the side.
     */
    private void moveRowsHorizontal() {
        for (AliensRow aliensRow : aliensColList) {
            aliensRow.moveHorizontal(moveDir, moveDis);
        }
    }

    /**
     * Adds an alienRow to the list.
     * @param aliensRow the alienRow to add
     */
    public void add(AliensRow aliensRow) {
        this.aliensColList.add(aliensRow);
    }

    /**
     * Removing a row from our list.
     * @param aliensRow the row to remove.
     */
    public void remove(AliensRow aliensRow) {
        this.aliensColList.remove(aliensRow);
    }

    /**
     * Adding the alien to the correct objects.
     * @param newGameLevel the GameLevel to join.
     */
    public void addToGame(GameLevel newGameLevel) {
        newGameLevel.addSprite(this);
    }

    /**
     * removeing the aliens col from the game.
     * @param currentGameLevel the GameLevel to remove from.
     */
    public void removeFromGame(GameLevel currentGameLevel) {
        //newGameLevel.addCollidable(this);
        currentGameLevel.removeSprite(this);
    }

    /**
     * Resets the aliens positions.
     */
    public void resetPos() {
        for (AliensRow aliensRow : this.aliensColList) {
            aliensRow.resetPos(50, 50);
        }

    }

    /**
     * Check if an Alien got to the shields.
     * @return if an Alien got to the shields.
     */
    private boolean checkIfAlienWon() {
        for (AliensRow aliensRow : this.aliensColList) {
            /* Gets the last Alien properties*/
            double height = aliensRow.getBottomAlien().getCollisionRectangle()
                    .getHeight();
            double y =  aliensRow.getBottomAlien().getCollisionRectangle()
                    .getUpperLeft().getY();
            /* Check if reached the shields */
            if (y + height >= SHIELDS_START_Y) {
                return true;
            }
        }
        return false;
    }

}
