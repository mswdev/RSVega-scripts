package sphiinx.script.private_script.puppy_air_orbs.mission;

import sphiinx.api.framework.goal.GoalList;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.worker.Worker;
import sphiinx.script.private_script.puppy_air_orbs.mission.worker.AirOrbWorkerHandler;

public class AirOrbsMission extends Mission {

    private final AirOrbWorkerHandler MANAGER = new AirOrbWorkerHandler(this);

    @Override
    public String getMissionName() {
        return "Puppy Air Orbs";
    }

    @Override
    public String getWorkerName() {
        Worker<AirOrbsMission> c = MANAGER.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<AirOrbsMission> c = MANAGER.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return false;
    }

    @Override
    public boolean canEnd() {
        return goals.hasReachedGoals();
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public int execute() {
        MANAGER.work();
        return 150;
    }

    @Override
    public void onMissionStart() {

    }

    @Override
    public void onMissionEnd() {

    }

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }
}

