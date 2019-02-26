package org.api.script.framework.mission;

import org.rspeer.ui.Log;

import java.util.Queue;

// [TODO - 2018-11-05]: Rewrite this class along with the Mission and WorkerHandler/Worker
public class MissionHandler {

    private Queue<Mission> missions;
    private boolean end;
    private boolean end_current;

    public MissionHandler(Queue<Mission> missions) {
        this.missions = missions;
    }

    /**
     * Executes the missions.
     *
     * @return The sleep time for the loop cycle.
     */
    public int execute() {
        if (missions == null || missions.isEmpty()) {
            end = true;
            return 100;
        }

        final Mission mission = missions.peek();
        if (!mission.hasStarted()) {
            Log.info(mission.getMissionName() + " mission started");
            mission.onMissionStart();
            mission.setStarted(true);
        }

        if (mission.shouldEnd() || end_current) {
            Log.info(mission.getMissionName() + " mission ended");
            mission.onMissionEnd();
            missions.poll();
            end_current = false;
            return 100;
        } else {
            if (mission.shouldPrintWorkerString())
                Log.info("[" + mission.getWorkerName() + "]: " + mission.getWorkerString());
            return mission.execute();
        }
    }

    /**
     * Gets the queue of missions.
     *
     * @return The queue containing the missions.
     */
    public Queue<Mission> getMissions() {
        return missions;
    }

    /**
     * Ends the current mission.
     */
    public void endCurrent() {
        end_current = true;
    }

    /**
     * Checks whether the mission is stopped.
     *
     * @return True if the mission is stopped; false otherwise.
     */
    public boolean isStopped() {
        return end;
    }

    /**
     * Gets the current mission.
     *
     * @return The current mission.
     */
    public Mission getCurrent() {
        return missions.peek();
    }
}

