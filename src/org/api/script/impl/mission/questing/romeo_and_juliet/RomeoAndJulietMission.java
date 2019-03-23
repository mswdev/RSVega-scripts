package org.api.script.impl.mission.questing.romeo_and_juliet;

import org.api.script.SPXScript;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.questing.romeo_and_juliet.data.RomeoAndJulietState;
import org.api.script.impl.mission.questing.romeo_and_juliet.worker.RomeoAndJulietWorkerHandler;

public class RomeoAndJulietMission extends Mission {

    public static final int ROMEO_AND_JULIET_VARP = 144;
    private final RomeoAndJulietWorkerHandler handler = new RomeoAndJulietWorkerHandler();

    public RomeoAndJulietMission(SPXScript script) {
        super(script);
    }

    @Override
    public String getMissionName() {
        return "Romeo & Juliet";
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
        return RomeoAndJulietState.isInVarp(RomeoAndJulietState.COMPLETE);
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
}

