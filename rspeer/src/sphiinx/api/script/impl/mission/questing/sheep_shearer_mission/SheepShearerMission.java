package sphiinx.api.script.impl.mission.questing.sheep_shearer_mission;

import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.questing.sheep_shearer_mission.data.SheepShearerState;
import sphiinx.api.script.impl.mission.questing.sheep_shearer_mission.worker.SheepShearerWorkerHandler;

public class SheepShearerMission extends Mission {

    public static final int SHEEP_SHEARER_VARP = 179;
    private final SheepShearerWorkerHandler handler = new SheepShearerWorkerHandler();

    @Override
    public String getMissionName() {
        return "Sheep Shearer";
    }

    @Override
    public String getWorkerName() {
        Worker c = handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = handler.getCurrent();
        return c == null ? "Waiting for worker" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
        return SheepShearerState.isInVarp(SheepShearerState.COMPLETE);
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

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }
}
