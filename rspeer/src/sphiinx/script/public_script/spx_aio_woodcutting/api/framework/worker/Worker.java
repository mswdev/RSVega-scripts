package sphiinx.script.public_script.spx_aio_woodcutting.api.framework.worker;

import sphiinx.script.public_script.spx_aio_woodcutting.api.framework.mission.Mission;

public abstract class Worker<T extends Mission> {

    protected T mission;

    public Worker(T mission) {
        this.mission = mission;
    }

    /**
     * Repeats the execution of the worker.
     *
     * @return True if the worker execution should be repeated; false otherwise.
     */
    public abstract boolean needsRepeat();

    /**
     * Executes the work of the worker.
     */
    public abstract void work();
}

