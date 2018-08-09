package sphiinx.api.framework.mission;

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
            System.out.println(MISSION.getMissionName() + " mission started");
            MISSION.onMissionStart();
            MISSION.setStarted(true);
        }

        if (MISSION.canEnd()) {
            System.out.println(MISSION.getMissionName() + " mission ended");
            MISSION.onMissionEnd();
            missions.poll();
            return 150;
        } else {
            if (MISSION.shouldPrintWorkerString())
                System.out.println("[" + MISSION.getWorkerName() + "]: " + MISSION.getWorkerString());
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

