package anime;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * A class of KeyPressStoppableAnimation.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * The constructor of KeyPressStoppableAnimation.
     * @param sensor the KeyboardSensor.
     * @param key the key we should stop on.
     * @param animation the anumation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Draw a frame of the animation until key is pressed.
     * @param d The draw surface to draw on.
     * @param dt The time it took from last run.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Returns if we should stop the animation.
     * @return if we should stop the animation.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
