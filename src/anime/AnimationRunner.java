package anime;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * A class of AnimationRunner.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private int framePerSecond;

    /**
     * The construction method of anime.AnimationRunner.
     * @param gui the GUI.
     * @param framePerSecond number of frames per second.
     */
    public AnimationRunner(GUI gui, int framePerSecond) {
        this.gui = gui;
        this.framePerSecond = framePerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * Running the animation provided.
     * @param animation the animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framePerSecond;
        while (!animation.shouldStop()) {
            // timing
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, 1.0 / this.framePerSecond);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }

        }
    }
}
