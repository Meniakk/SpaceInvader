package gameobjects;

/**
 * A class of MenuSelection.
 * @param <T> the generic object.
 */
public class MenuSelection<T> {

    private String key;
    private String message;
    private T returnVal;

    /**
     * Constructor for MenuSelection.
     * @param key the new key.
     * @param message the new message.
     * @param returnVal the new return value.
     */
    public MenuSelection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * Returns the key.
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns the message.
     * @return the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the return value.
     * @return the return value.
     */
    public T getReturnVal() {
        return this.returnVal;
    }
}
