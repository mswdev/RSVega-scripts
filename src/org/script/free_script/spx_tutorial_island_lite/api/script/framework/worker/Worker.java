package org.script.free_script.spx_tutorial_island_lite.api.script.framework.worker;

public abstract class Worker {


    /**
     * Repeats the execution of the worker.
     */
    public abstract boolean needsRepeat();

    /**
     * Executes the work of the worker.
     */
    public abstract void work();

    public abstract String toString();
}

