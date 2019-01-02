package gameobjects;

import anime.AnimationRunner;
import anime.ScoreIndicator;
import anime.LivesIndicator;
import anime.LevelIndicator;
import anime.KeyPressStoppableAnimation;
import anime.PauseScreen;
import anime.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.GUI;
import enemies.Alien;
import enemies.AlienCol;
import enemies.AliensRow;
import interfaces.Animation;
import interfaces.HitListener;
import interfaces.LevelInformation;
import interfaces.Sprite;
import interfaces.Collidable;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import geometry.Rectangle;
import saves.HighScoresTable;

/**
 * A class of gameobjects.GameLevel.
 */
public class GameLevel implements Animation {

    private AnimationRunner runner;
    private GUI gui;
    private Sleeper sleeper;
    private KeyboardSensor keyboard;
    private boolean firstRun = true;
    private boolean playerDead = false;
    private boolean running;
    private boolean wonLastOne;
    private SpriteCollection sprites;
    private AlienCol gameAlienCol;
    private GameEnvironment environment;
    private LevelInformation levelInformation;
    private List<Ball> bulletsList = new ArrayList<>();
    private List<Block> shieldsList = new ArrayList<>();
    private LevelIndicator gameLevelIndicator;
    private Counter numOfBlocks;
    private Counter numOfBalls;
    private Counter score;
    private Counter lives;
    private HighScoresTable highScoresTable;

    /* GIU size. */
    private static final int GUI_WIDTH = 800;
    /* FPS */
    private static final int FPS = 60;
    /* Aliens movement */
    private static int battleNumber = 0;
    /* HitListeners. */
    private HitListener blockRemover;
    private HitListener ballsRemover;
    private HitListener scoreTracker;

    /**
     * The constructor method of GameLevel.
     * @param levelInformation the level info.
     * @param keyboardSensor keyboardSensor.
     * @param score the user score.
     * @param gui the games`s GUI.
     * @param lives the games lives.
     */
    public GameLevel(LevelInformation levelInformation,
                     KeyboardSensor keyboardSensor,
                     GUI gui, Counter score, Counter lives) {

        /* Creating the GUI and Sleeper. */
        this.gui = gui;
        this.sleeper = new Sleeper();

        /* Creating the lists */
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        this.score = score;
        this.lives = lives;
        numOfBlocks = new Counter(0);
        numOfBalls = new Counter(0);

        /* Getting game info */
        this.runner = new AnimationRunner(this.gui, FPS);
        this.keyboard = keyboardSensor;
        this.levelInformation = levelInformation;

        /* Number of battle */
        battleNumber++;
    }

    /**
     * Initialize a new game:
     * Create's the Blocks, Balls, Frames and gameobjects.Paddle,
     * and add's them to the game.
     */
    public void initialize() {


        /* Listeners */
        this.blockRemover = new BlockRemover(this, this.numOfBlocks);
        this.ballsRemover = new BallRemover(this, this.numOfBalls);
        this.scoreTracker = new ScoreTrackingListener(this.score);

        /* Adding the Background. */
        this.sprites.addSprite(this.levelInformation.getBackground());

        /* Creating the shields. */
        createShields();

        /* Creating Blocks. */
        this.createBlocks();

        /* Creating the score-bar. */
        this.createScoreBar();

        /* Creating the lives-bar. */
        this.createLivesBar();

        /* Creating level bar */
        this.createLevelBar();

    }

    /**
     * Creates a game's blocks.
     */
    public void createBlocks() {

        /* death-region block */
        Rectangle killer = new Rectangle(-100, 620,
                1000, 100);
        Block killerBlock = new Block(killer, Color.black);
        killerBlock.addHitListener(ballsRemover);
        this.environment.addCollidable(killerBlock);

        /* Adding the num of blocks to the counter */
        this.numOfBlocks.increase(this.levelInformation.numberOfBlocksToRemove());

        /* Adding the Col to sprites so we can control the shootings*/
        AlienCol alienCol = this.levelInformation.blocks();
        alienCol.addToGame(this);
        this.gameAlienCol = alienCol;

        /* Adding the aliens */
        for (AliensRow aliensRow : alienCol.getAliensColList()) {
            for (Alien alien : aliensRow.getAliensRow()) {
                alien.setGameLevel(this);
                addCollidable(alien);
                alien.addHitListener(blockRemover);
                alien.addHitListener(scoreTracker);
            }
        }
    }

    /**
     * Creates the game's paddle.
     * @return the paddle we created.
     */
    private Paddle createPaddle() {

        /* Creating the paddle. */
        Paddle paddle = new Paddle(gui,
                this.levelInformation.paddleSpeed(),
                this.levelInformation.paddleWidth(), this);
        paddle.addToGame(this);
        return paddle;
    }

    /**
     * Creates the shields.
     */
    private void createShields() {

        int shieldSize = 3;
        int shieldSpace = 50;
        int startX = 100;
        int startY = 500;
        int currentX = startX;
        int currentY = startY;

        for (int b = 0; b < 3; b++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 50; j++) {
                    Rectangle rec = new Rectangle(currentX, currentY, shieldSize, shieldSize);
                    Block block = new Block(rec, Color.blue);
                    block.addToGame(this);
                    block.addHitListener(blockRemover);
                    this.shieldsList.add(block);
                    currentX += shieldSize;
                } /* End of loop i */
                currentX += shieldSpace;
            } /* End of loop j */
            currentY += shieldSize;
            currentX = startX;
        } /* End of loop b */

    }

    /**
     * Adding the score-bar to the game.
     */
    private void createScoreBar() {
        Rectangle scoreRec = new Rectangle(0, 0, GUI_WIDTH, 20);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreRec, this
                .score);
        this.addSprite(scoreIndicator);
    }

    /**
     * Creates the lives bar.
     */
    private void createLevelBar()    {
        String name = this.levelInformation.levelName();
        LevelIndicator levelIndicator = new LevelIndicator(name);
        this.gameLevelIndicator = levelIndicator;
        this.addSprite(levelIndicator);
    }

    /**
     * Adding the lives-bar to the game.
     */
    private void createLivesBar() {
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        this.addSprite(livesIndicator);
    }

    /**
     * Decrease the amount of remaining blocks on screen by 1.
     */
    public void updateBlocks() {
        this.numOfBlocks.decrease(1);
    }

    /**
     * Decrease the amount of remaining balls on screen by 1.
     */
    public void updateBalls() {
        this.numOfBalls.decrease(1);
    }

    /**
     * Adds a interfaces.Collidable to the list.
     * @param c the interfaces.Collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a Sprite to the list.
     * @param s the Sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a interfaces.Collidable from the game.
     * @param c the interfaces.Collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a Sprite from the game.
     * @param s the Sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Return if to stop the turn.
     * @return boolean, if to stop the turn.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Updating the objects.
     * @param d The draw surface to draw on.
     * @param dt The time it takes to draw.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);


        /* If we won */
        if (this.numOfBlocks.getValue() == 0) {
            this.wonLastOne = true;
            this.running = false;
        }

        /* If lost */
        if (playerDead) {
            this.wonLastOne = false;
            this.running = false;
        }

        /* Pausing. */
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation runIt =
                    new KeyPressStoppableAnimation(
                            keyboard, "space", new PauseScreen());
            this.runner.run(runIt);
        }

    }

    /**
     * Run the game - start the animation loop.
     */
    public void playOneTurn() {

        if (firstRun) {
            firstRun = false;
            battleNumber = 1;
        } else {
            /* We lost the prev. one */
            if (!wonLastOne) {
                this.resetAliensPos();
            } else { /* If we won the prev. one */
                /* Raise the battle num. */
                battleNumber++;
                /* Reloading aliens and shields */
                this.gameAlienCol.removeFromGame(this);
                createBlocks();
                resetShield();
            }
            /* Removing all the bullets */
            this.removeAllBullets();
        }

        /* Sets level name */
        this.gameLevelIndicator.setLevelName("Battle no. " + battleNumber);
        /* Sets aliens speed */
        this.gameAlienCol.setMoveDis(battleNumber);
        this.gameAlienCol.setMoveDir(1);


        /* Creating the paddle. */
        Paddle paddle = createPaddle();


        this.runner.run(new CountdownAnimation(2, 3, this.sprites));

        /* Reset game booleans */
        this.running = true;
        this.playerDead = false;


        /* Run the level with the animation runner. */
        this.runner.run(this);

        /* Removing the paddle */
        paddle.removeFromGame(this);

    }

    /**
     * Returns the number of balls on screen.
     * @return the number of balls on screen.
     */
    public int getBallsNumber() {
        return this.numOfBalls.getValue();
    }

    /**
     * Returns the number of blocks on screen.
     * @return the number of blocks on screen.
     */
    public int getBlocksNumber() {
        return this.numOfBlocks.getValue();
    }

    /**
     * Returns the GameEnvironment.
     * @return GameEnvironment - the GameEnvironment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Returns the score table.
     * @return the score table.
     */
    public HighScoresTable getHighScoresTable() {
        return this.highScoresTable;
    }

    /**
     * Flags that the player was killed.
     */
    public void setPlayerDead() {
        this.playerDead = true;
    }

    /**
     * Resets the aliens positions.
     */
    private void resetAliensPos() {
        this.gameAlienCol.resetPos();
    }

    /**
     * Adds a bullet to the game.
     * @param bullet the bullet to add.
     */
    public void addBullet(Ball bullet) {
        this.bulletsList.add(bullet);
    }

    /**
     * removes a bullet from the game.
     * @param bullet the bullet to remove.
     */
    public void removeBullet(Ball bullet) {
        this.bulletsList.remove(bullet);
    }

    /**
     * Removes all the bullets from this game.
     */
    private void removeAllBullets() {
            List<Ball> temp = new ArrayList<>(this.bulletsList);
            for (Ball ball : temp) {
                ball.removeFromGame(this);
            }
    }

    /**
     * Reset's the shields bt removing and creating again.
     */
    private void resetShield() {
        List<Block> temp = new ArrayList<>(this.shieldsList);
        for (Block block : temp) {
            block.removeFromGame(this);
        }
        this.shieldsList.clear();
        createShields();
    }

    /**
     * Calling playOneTurn to start the game,
     * after a loss/win update lives.
     * If more lives are available call the game again,
     * else finish the game.
     */
    public void run() {
        while (this.lives.getValue() > 0) {
            this.playOneTurn();
            this.lives.decrease(1);
        }
        this.gui.close();
        return;
    }

}
