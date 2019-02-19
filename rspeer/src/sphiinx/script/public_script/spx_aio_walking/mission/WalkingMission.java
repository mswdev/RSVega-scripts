package sphiinx.script.public_script.spx_aio_walking.mission;

import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.WalkingWorkerHandler;

public class WalkingMission extends Mission {

    private final WalkingWorkerHandler handler = new WalkingWorkerHandler();

    public WalkingMission(SPXScript script) {
        super(script);
    }

    @Override
    public String getMissionName() {
        return "Walking";
    }

    @Override
    public String getWorkerName() {
        Worker c = handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = handler.getCurrent();
        return c == null ? "WORKER" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean shouldEnd() {
        return false;
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public int execute() {
        handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {

    }

    @Override
    public void onMissionEnd() {

    }
}

