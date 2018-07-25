package sphiinx.api.framework.mission;

import sphiinx.api.util.Logging;

import java.util.Queue;

public class MissionHandler {

    private Queue<Mission> missions;
    private boolean stop;

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
            stop = true;
            return 150;
        }

        final Mission MISSION = missions.peek();
        if (!MISSION.hasStarted()) {
            MISSION.onMissionStart();
            MISSION.setStarted(true);
        }

        if (MISSION.canEnd()) {
            Logging.log(this, true, MISSION.getMissionName() + " mission has ended.");
            Logging.log(this, true, MISSION.getEndMessage());
            MISSION.onMissionEnd();
            missions.poll();
            return 150;
        } else {
            return MISSION.execute();
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
     * Checks whether the mission is stopped.
     *
     * @return True if the mission is stopped; false otherwise.
     */
    public boolean isStopped() {
        return stop;
    }
}

