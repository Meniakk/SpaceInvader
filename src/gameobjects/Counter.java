package gameobjects;

/**
 * A calss of gameobjects.Counter.
 */
public class Counter {

    private int count;

    /**
     * The constructor method of gameobjects.Counter.
     * @param number the new number.
     */
     public Counter(int number) {
         this.count = number;
     }

    /**
     * Add's a number to current count.
     * @param number the number to add.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtract's a number to current count.
     * @param number the number to subtract.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Get current count.
     * @return the current count.
     */
    public int getValue() {
        return this.count;
    }

    /**
     * Return a string representing the object.
     * @return a string representing the object.
     */
    public String toString() {
        return Integer.toString(this.count);
    }
}
