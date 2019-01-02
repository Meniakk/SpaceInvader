package level;

import biuoop.DrawSurface;
import enemies.Alien;
import enemies.AlienCol;
import enemies.AliensRow;
import geometry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Returns the level to play.
 */
public class Level implements LevelInformation {

    private static final int BLOCKS = 50;
    private static final int PADDLE_SPEED = 5 * 60;
    private static final int PADDLE_WIDTH = 40;
    private static final String LEVEL_NAME = "Battle no. 1";

    /**
     * Returns the paddle speed.
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Returns the paddle width.
     * @return the paddle width.
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Returns the level name.
     * @return the level name.
     */
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * Returns a sprite with the background of the level.
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(0, 0, 800, 600);

            }

            @Override
            public void timePassed(double dt) {

            }
        };
    }

    /**
     * Returns a list of blocks.
     * @return a list of blocks.
     */
    public AlienCol blocks() {
        Image image = loadImage();
        int startX = 50;
        int startY = 50;
        int dif = 10;
        AlienCol alienCol = new AlienCol();

        for (int j = 0; j < 10; j++) {
            AliensRow aliensRow = new AliensRow(new ArrayList<>(), j);
            for (int i = 0; i < 5; i++) {
                Rectangle rec = new Rectangle(
                        (startX + (40 * j) + (j * dif)),
                        (startY + (30 * i) + (i * dif)),
                        40,
                        30);
                aliensRow.add(new Alien(image, rec, i));
            }
            alienCol.add(aliensRow);
        }
        return alienCol;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return the number of blocks.
     */
    public int numberOfBlocksToRemove() {
        return BLOCKS;
    }

    /**
     * Loads the alien Image.
     * @return The alien Image.
     */
    private Image loadImage() {
        Image img = null;
        try {
            InputStream inputStream = ClassLoader.
                    getSystemResourceAsStream("resources\\enemy.png");
            img = ImageIO.read(inputStream);
        } catch (IOException e) {
            img = null;
        }
        return img;
    }

}
