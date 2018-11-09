package sphiinx.script.public_script.spx_tutorial_island.api.framework.worker;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;

public abstract class Worker<T extends Mission> {

    protected T mission;

    public Worker() {
    }

    public Worker(T mission) {
        this.mission = mission;
    }

    /**
     * Repeats the execution of the worker.
     *
     * @return True if the worker execution should be repeated; false otherwise.
     */
    public boolean needsRepeat() {
        return false;
    }

    /**
     * Executes the work of the worker.
     */
    public abstract void work();
}

