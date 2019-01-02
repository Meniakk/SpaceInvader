package interfaces;

/**
 * A class of Task.
 * @param <T> The generic object.
 */
public interface Task<T> {

    /**
     * A task to run.
     * @return the result of task.
     */
    T run();

}
