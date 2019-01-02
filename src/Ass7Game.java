import anime.MenuAnimation;
import anime.AnimationRunner;
import anime.KeyPressStoppableAnimation;
import anime.HighScoresAnimation;
import biuoop.GUI;
import gameobjects.GameFlow;
import interfaces.Task;
import level.Level;
import saves.HighScoresTable;
import java.io.File;
import java.io.IOException;


/**
 * The main class.
 */
public class Ass7Game {

    /* GIU size. */
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * The main of the project.
     * @param args the user input.
     */
    public static void main(String[] args) {

        /* Setting the GUI and animation runner */
        GUI gui = new GUI("SPACE-INVADERS", GUI_WIDTH, GUI_HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        /* Creating a gameFlow object. */
        GameFlow gameFlow = new GameFlow(gui, animationRunner, gui.getKeyboardSensor());

        /* Creates the main menu tasks (anonymous classes) and the main menu */
        MenuAnimation<Task<Void>> menu =
                new MenuAnimation<>(gui.getKeyboardSensor(), "SPACE-INVADERS");

        Task<Void> startTask = new Task<Void>() {
            @Override
            public Void run() {
                /* Starting the sun-menu. */
                try {
                    gameFlow.runLevel(new Level());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        };
        Task<Void> hSTask = new Task<Void>() {
            @Override
            public Void run() {
                try {
                    /* Showing the high scores screen. */
                    KeyPressStoppableAnimation runIt =
                            new KeyPressStoppableAnimation(
                                    gui.getKeyboardSensor(), "space",
                                    new HighScoresAnimation(
                                            HighScoresTable.loadFromFile(new
                                                    File(GameFlow.HS_PATH))));
                    animationRunner.run(runIt);


                } catch (IOException e) {
                    System.out.println("");
                }
                return null;
            }
        };
        Task<Void> quitTask = new Task<Void>() {
            @Override
            public Void run() {
                /* Closing the GUI. */
                gui.close();
                /* Exiting the program. */
                System.exit(0);
                return null;
            }
        };
        menu.addSelection("s", "(s) Start", startTask);
        menu.addSelection("h", "(h) High-Scores", hSTask);
        menu.addSelection("q", "(q) Quit", quitTask);

        /* The games loop */
        while (true) {
            /* Showing menu */
            animationRunner.run(menu);
            /* Running task */
            menu.getStatus().run();

            /* reset menus */
            menu.resetMenu();
        }
    }
}
