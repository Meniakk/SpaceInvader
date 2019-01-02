package enemies;

import biuoop.DrawSurface;
import gameobjects.Block;
import gameobjects.GameLevel;
import gameobjects.Ball;
import java.awt.Image;
import geometry.Point;
import geometry.Rectangle;


/**
 * A class of Alien.
 */
public class Alien extends Block {
    private Image image;
    private GameLevel gameLevel;
    private boolean alive = true;
    private int numberInRow;

    /**
     * The constructor.
     * @param image The alien IMG
     * @param rectangle The alien logic position.
     * @param number The alien number in his row.
     */
    public Alien(Image image, Rectangle rectangle, int number) {
        this.image = image;
        super.setRectangle(rectangle);
        this.numberInRow = number;
    }

    /**
     * Drawing the alien.
     * @param d On what to draw.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(
                (int) super.getRectangle().getUpperLeft().getX(),
                (int) super.getRectangle().getUpperLeft().getY(),
                this.image);
    }

    /**
     * Removing the block from the game.
     * @param newGameLevel the game to remove the block from.
     */
    public void removeFromGame(GameLevel newGameLevel) {
        super.removeFromGame(newGameLevel);
        this.alive = false;
    }

    /**
     * Returning whether the alien is alive.
     * @return if the alien is alive.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Setting the game.
     * @param newGameLevel the game.
     */
    public void setGameLevel(GameLevel newGameLevel) {
        this.gameLevel = newGameLevel;
    }

    /**
     * Make the alien shoot.
     */
    public void shoot() {

        int deltaX = (int) (0.5 * super.getRectangle().getWidth());
        int deltaY = (int) (super.getRectangle().getHeight() + 1);
        int x = (int) super.getRectangle().getUpperLeft().getX() + deltaX;
        int y = (int) super.getRectangle().getUpperLeft().getY() + deltaY;
        Point point = new Point(x, y);

        Ball attack = new Ball(point, false, gameLevel);
        attack.shoot();

    }

    /**
     * Tells if the object is friendly to the player.
     * @return boolean if the object is friendly to the player.
     */
    public boolean isFriend() {
        return false;
    }

    /**
     * Resets the aliens positions.
     * @param xPos the X of the aliens in the current row.
     * @param yPos the Y of the aliens in the current col.
     */
    public void resetPos(int xPos, int yPos) {
        int mult = this.numberInRow;
        int y = yPos + (mult * 30) + (mult * 10);

        super.getRectangle().setUpperLeft(new Point(xPos, y));

    }

    /**
     * If aliens won tell the gameLevel.
     */
    public void alienWon() {
        this.gameLevel.setPlayerDead();
    }
}
