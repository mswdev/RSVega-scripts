package sphiinx.api.script.impl.mission.questing.rune_mysteries_mission;

import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.questing.rune_mysteries_mission.data.RuneMysteriesState;
import sphiinx.api.script.impl.mission.questing.rune_mysteries_mission.worker.RuneMysteriesWorkerHandler;

public class RuneMysteriesMission extends Mission {

    public static final int RUNE_MYSTERIES_VARP = 63;
    private final RuneMysteriesWorkerHandler handler = new RuneMysteriesWorkerHandler();

    public RuneMysteriesMission(SPXScript script) {
        super(script);
    }

    @Override
    public String getMissionName() {
        return "Rune Mysteries";
    }

    @Override
    public String getWorkerName() {
        Worker c = handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = handler.getCurrent();
        return c == null ? "Waiting for worker." : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean shouldEnd() {
        return RuneMysteriesState.isInVarp(RuneMysteriesState.COMPLETE);
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

