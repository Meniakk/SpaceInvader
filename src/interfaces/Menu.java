package interfaces;

/**
 * An interface of menu.
 * @param <T> The generic element.
 */
public interface Menu<T> extends Animation {

    /**
     * Add's a selection to menu.
     * @param key The key.
     * @param message The message to display for key.
     * @param returnVal The value to return if selected.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Returns the selected value.
     * @return the selected value.
     */
    T getStatus();

}
