package sphiinx.api.framework.mission;

import sphiinx.api.framework.goal.GoalList;

public abstract class Mission {

    protected GoalList goals;
    private boolean has_started;

    public Mission() {
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
     * Determines whether the mission can end.
     *
     * @return True if the mission can end, false otherwise.
     */
    public abstract boolean canEnd();

    /**
     * Gets the message the end of mission message.
     *
     * @return The end of mission message.
     */
    public abstract String getEndMessage();

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

    //TODO ADD PAINT SUPPORT
    //TODO ADD PAINT SUPPORT
    //TODO ADD PAINT SUPPORT
    /**
     * Generates the mission-specific paint Strings for this mission This is combined with the VikingScript specific
     * paint Strings seamlessly into one paint
     *
     * @return An array of Strings representing the mission specific paint info, or null if there is none
     */
    public abstract String[] getMissionPaint();

    /**
     * Resets the mission specific paint info
     */
    public abstract void resetPaint();

}

