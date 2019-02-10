package sphiinx.script.public_script.spx_tutorial_island.api.script.framework.mission;

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
            System.out.println(mission.getMissionName() + " mission started");
            mission.onMissionStart();
            mission.setStarted(true);
        }

        if (mission.canEnd() || end_current) {
            System.out.println(mission.getMissionName() + " mission ended");
            mission.onMissionEnd();
            missions.poll();
            end_current = false;
            return 100;
        } else {
            if (mission.shouldPrintWorkerString())
                System.out.println("[" + mission.getWorkerName() + "]: " + mission.getWorkerString());
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
     * */
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
}

