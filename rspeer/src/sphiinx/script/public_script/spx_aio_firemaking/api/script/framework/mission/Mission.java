package sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.mission;

import sphiinx.script.public_script.spx_aio_firemaking.api.script.SPXScript;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.goal.GoalList;

public abstract class Mission {

    protected SPXScript script;
    protected GoalList goals;
    private boolean has_started;

    public Mission(SPXScript script) {
        this.script = script;
        goals = getGoals();
    }

    /**
     * Gets the mission name.
     *
     * @return The mission name.
     */
    public abstract String getMissionName();

    /**
     * Gets the current worker name.
     *
     * @return The current worker name.
     */
    public abstract String getWorkerName();

    /**
     * Gets the current worker string.
     *
     * @return The current worker string.
     */
    public abstract String getWorkerString();

    /**
     * True if the current worker string should be printed.
     *
     * @return True if the current worker string should be printed; false otherwise.
     */
    public abstract boolean shouldPrintWorkerString();

    /**
     * Determines whether the mission should end.
     *
     * @return True if the mission should end, false otherwise.
     */
    public abstract boolean shouldEnd();

    /**
     * Gets the goals for the mission.
     *
     * @return The goals for the mission.
     */
    public abstract GoalList getGoals();

    /**
     * Execution and main method for the mission.
     *
     * @return The sleep time for the cycle.
     */
    public abstract int execute();

    /**
     * Executes on the start of the mission.
     */
    public abstract void onMissionStart();

    /**
     * Executes at the end of the mission.
     */
    public abstract void onMissionEnd();

    /**
     * Checks if the mission has started.
     *
     * @return True if the mission has started; false otherwise.
     */
    public boolean hasStarted() {
        return has_started;
    }

    /**
     * Sets whether the mission has started.
     */
    public void setStarted(boolean started) {
        has_started = started;
    }

    /**
     * Gets the script.
     *
     * @return The script.
     */
    public SPXScript getScript() {
        return script;
    }

}

