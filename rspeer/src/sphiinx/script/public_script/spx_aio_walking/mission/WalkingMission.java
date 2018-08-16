package sphiinx.script.public_script.spx_aio_walking.mission;

import sphiinx.api.framework.goal.GoalList;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.WalkingWorkerHandler;

public class WalkingMission extends Mission {

    public final WalkingWorkerHandler MANAGER = new WalkingWorkerHandler(this);

    @Override
    public String getMissionName() {
        return "Walking";
    }

    @Override
    public String getWorkerName() {
        Worker<WalkingMission> c = MANAGER.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<WalkingMission> c = MANAGER.getCurrent();
        return c == null ? "WORKER" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
        return false;
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

