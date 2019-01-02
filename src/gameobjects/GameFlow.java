package gameobjects;

import anime.AnimationRunner;
import anime.EndScreen;
import anime.HighScoresAnimation;
import anime.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import saves.HighScoresTable;
import saves.ScoreInfo;
import java.io.File;
import java.io.IOException;

/**
 * A class of GameFlow.
 */
public class GameFlow {

    /* High Scores */
    public static final String HS_PATH = "highscores.txt";
    public static final int HST_SIZE = 5;
    private static final int LIVES = 3;
    private HighScoresTable highScoresTable;

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter score;
    private Counter lives;


    /**
     * Returns the KeyboardSensor.
     * @return the KeyboardSensor.
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }


    /**
     * A constructor for GameFlow.
     * @param animation the animation runner.
     * @param keyboard the keyboard sensor.
     * @param gui the game gui.
     */
    public GameFlow(GUI gui, AnimationRunner animation, KeyboardSensor
            keyboard) {
        this.animationRunner = animation;
        this.gui = gui;
        this.keyboardSensor = keyboard;
        this.score = new Counter(0);
        this.lives = new Counter(LIVES);
        createHighScoreTable();
    }

    /**
     * Running the levels from the list.
     * @param level the level to play.
     */
    public void runLevel(LevelInformation level) {

        //todo take care of shields
        GameLevel gameLevel = null;
        gameLevel = new GameLevel(level, keyboardSensor, gui, score, lives);
        gameLevel.initialize();

        /*
        * while we have more life play the game.
        */
        while (this.lives.getValue() > 0) {

            gameLevel.playOneTurn();

            if (gameLevel.getBlocksNumber() > 0) {
                this.lives.decrease(1);
            }

        }




        /* Display end screen */
        if (gameLevel != null) {
            boolean won = (gameLevel.getBallsNumber() != 0);

            KeyPressStoppableAnimation runIt =
                    new KeyPressStoppableAnimation(
                            this.keyboardSensor, "space",
                            new EndScreen(won, this.score.getValue()));
            this.animationRunner.run(runIt);
        }

        /* Checking if score should be in high scores. */
        int currentScore = this.score.getValue();

        int rank = highScoresTable.getRank(currentScore);
        if (rank <= HST_SIZE) {
            /* Getting player's name */
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Get Name",
                    "What's your name?", "");
            /* Adding to table and saving*/
            ScoreInfo scoreInfo = new ScoreInfo(name, currentScore);
            this.highScoresTable.add(scoreInfo);
            try {
                this.highScoresTable.save(new File(HS_PATH));
            } catch (IOException e) {
                System.out.println("");
            }
        }

        /* Resetting lives, score, and re-loading the table. */
        reset();

        /* Showing the high scores screen. */
        KeyPressStoppableAnimation runIt =
                new KeyPressStoppableAnimation(
                        keyboardSensor, "space",
                        new HighScoresAnimation(
                                this.highScoresTable));
        this.animationRunner.run(runIt);

    }

    /**
     * Resetting the object for reusable use.
     */
    private void reset() {

        /* Reset the values. */
        this.score = new Counter(0);
        this.lives = new Counter(1);
        try {
            this.highScoresTable.load(new File(HS_PATH));
        } catch (IOException e) {
            System.out.println("");
        }
    }

    /**
     * Creating the game's high score table.
     */
    private void createHighScoreTable() {
        HighScoresTable highScores = new HighScoresTable(HST_SIZE);
        /* if file exist. */
        if (isHighScoreFileExist()) {
            try {
                highScores.load(new File(HS_PATH));
                this.highScoresTable = highScores;
            } catch (IOException e) {
                this.highScoresTable = null;
            }
        } else { /* file does not exist. */
            try {
                highScores.save(new File(HS_PATH));
                this.highScoresTable = highScores;
            } catch (IOException e) {
                this.highScoresTable = highScores;
            }
        }

    }

    /**
     * Checks if the highscores file exist.
     * @return true if exist, false else.
     */
    private boolean isHighScoreFileExist() {
        File f = new File(HS_PATH);
        return (f.exists() && !f.isDirectory());
    }

}
