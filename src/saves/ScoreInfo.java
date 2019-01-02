package saves;

/**
 * A class of ScoreInfo.
 */
public class ScoreInfo {

    private String name;
    private int score;

    /**
     * A constructor method for class.
     * @param name The name.
     * @param score The score.
     */
    public  ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the name.
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the score.
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Returns the object as string.
     * @return the object as string.
     */
    public String toString() {
        return "Name: " + this.name + " Score: " + this.score;
    }
}
