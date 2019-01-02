package anime;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameobjects.MenuSelection;
import interfaces.Animation;
import interfaces.Menu;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of MenuAnimation.
 * @param <T> The generic object.
 */
public class MenuAnimation<T> implements Menu<T>, Animation {

    private List<MenuSelection<T>> selections;
    private T selected;
    private boolean stop;
    private KeyboardSensor keyboardSensor;
    /* Used to display title */
    private String title;
    private static int move = 1;
    private static int basicYUp = 150;
    private static int basicYDown = 150;
    /* Menu background color */
    private static Color backgroundColor = Color.black;
    /* Used for menu animation */
    private static int recX = 400;
    private static int recY = 300;
    private static int recSize = 0;
    private static Color recColor = Color.darkGray;


    /**
     * The constructor of MenuAnimation.
     * @param title The menu's title.
     * @param sensor The keyboard sensor.
     */
    public MenuAnimation(KeyboardSensor sensor, String title) {
        this.selections = new ArrayList<>();
        this.stop = false;
        this.keyboardSensor = sensor;
        this.title = title;
    }

    /**
     * Add's a selection to menu.
     * @param key The key.
     * @param message The message to display for key.
     * @param returnVal The value to return if selected.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new MenuSelection<>(key, message, returnVal));
    }

    /**
     * Returns the selected value.
     * @return the selected value.
     */
    public T getStatus() {
        return this.selected;
    }

    /**
     * Draws the objects.
     * @param d The draw surface to draw on.
     * @param dt The time passed.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        /* Background */
        d.setColor(backgroundColor);
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(recColor);
        recSize += 5;
        d.fillRectangle(
                (int) (recX - (0.5 * (recSize + 200))),
                (int) (recY - (0.5 * recSize)),
                (recSize + 200),
                (recSize));

        if (recSize > 600) {
            backgroundColor = recColor;
            recSize = 0;

            if (recColor == Color.black) {
                recColor = Color.darkGray;
            } else if (recColor == Color.darkGray) {
                recColor = Color.gray;
            } else if (recColor == Color.gray) {
                recColor = Color.lightGray;
            } else if (recColor == Color.lightGray) {
                recColor = Color.black;
            }
        }

        /* Display Title */
        basicYUp += move;
        basicYDown -= move;
        for (int i = 0; i < this.title.length(); i++) {
            char ch = this.title.charAt(i);
            int x = 80 + (i * 50);

            /* Set color in switch case according to the char */
            switch (ch) {
                case 'A':
                    d.setColor(Color.yellow);
                break;
                case 'S':
                    d.setColor(Color.orange);
                break;
                case 'P':
                    d.setColor(Color.green);
                break;
                case 'C':
                    d.setColor(Color.magenta);
                break;
                case 'E':
                    d.setColor(Color.CYAN);
                break;
                case 'I':
                    d.setColor(Color.blue);
                break;
                case 'N':
                    d.setColor(Color.pink);
                break;
                case 'V':
                    d.setColor(Color.WHITE);
                    break;
                case 'R':
                    d.setColor(Color.orange);
                    break;
                case 'D':
                    d.setColor(Color.yellow);
                    break;
                default:
                    break;
            }

            /* Write char */
            if (i % 2 == 0) {
                d.drawText(x, basicYUp, Character.toString(ch), 70);
            } else {
                d.drawText(x, basicYDown, Character.toString(ch), 70);
            }
        }

        /* Resetting move to get a range of movement. */
        if (basicYUp > 170 || basicYUp < 130) {
            move *= -1;
        }

        if (this.title.startsWith("Sub")) {
            d.setColor(Color.white);
            d.drawText(200, 220, "Choose Level set:", 40);
        }


        /* Display options AND check for key pressed. */
        d.setColor(Color.white);
        for (int i = 0; i < this.selections.size(); i++) {

            /* Display options */
            MenuSelection<T> option = this.selections.get(i);
            d.drawText(200, 300 + (i * 70), option.getMessage(), 50);
            /* check for key pressed */
            if (this.keyboardSensor.isPressed(option.getKey())) {
                this.selected = option.getReturnVal();
                this.stop = true;
            }
        }

    }

    /**
     * Notify when to stop.
     * @return boolean, True if we should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Resetting the menu so it will be reusable.
     */
    public void resetMenu() {
        this.stop = false;
        this.selected = null;
    }

}
