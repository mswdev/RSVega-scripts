package org.api.script.framework.goal;

public interface Goal {

    /**
     * Gets the name of the goal.
     *
     * @return The name of the goal.
     */
    String getName();

    /**
     * Determines if the goal has been reached.
     *
     * @return True if the goal has been reached; false otherwise.
     */
    boolean hasReached();

    /**
     * The completion message of the goal.
     *
     * @return The completion message of the goal.
     */
    String getCompletionMessage();

}

