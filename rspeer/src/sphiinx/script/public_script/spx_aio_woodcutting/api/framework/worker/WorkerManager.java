package sphiinx.script.public_script.spx_aio_woodcutting.api.framework.worker;


import sphiinx.script.public_script.spx_aio_woodcutting.api.framework.mission.Mission;

public abstract class WorkerManager<T extends Mission> {

    protected T mission;
    protected Worker<T> current_worker;

    public WorkerManager(T mission) {
        this.mission = mission;
    }

    /**
     * Handles the flow of the mission to decide what worker to execute.
     *
     * @return The worker to execute.
     */
    public abstract Worker<T> decide();

    /**
     * Handles the execution of the workers and repetition.
     */
    public void work() {
        current_worker = current_worker == null || !current_worker.needsRepeat() ? decide() : current_worker;
        current_worker.work();
    }

    /**
     * Gets the current worker.
     *
     * @return The current worker.
     */
    public Worker<T> getCurrent() {
        return current_worker;
    }

}

