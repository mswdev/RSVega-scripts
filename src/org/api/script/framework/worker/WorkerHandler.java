package org.api.script.framework.worker;


public abstract class WorkerHandler {

    private Worker current_worker;

    /**
     * Handles the flow of the mission to decide what worker to execute.
     *
     * @return The worker to execute.
     */
    public abstract Worker decide();

    /**
     * Handles the execution of the workers and repetition.
     */
    public void work() {
        current_worker = current_worker == null || !current_worker.needsRepeat() ? decide() : current_worker;

        if (current_worker != null)
            current_worker.work();
    }

    /**
     * Gets the current worker.
     *
     * @return The current worker.
     */
    public Worker getCurrent() {
        return current_worker;
    }

}

