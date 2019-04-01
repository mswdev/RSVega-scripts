package org.api.script.impl.mission.questing.restless_ghost_mission;

import org.api.script.SPXScript;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.questing.restless_ghost_mission.data.RestlessGhostState;
import org.api.script.impl.mission.questing.restless_ghost_mission.worker.RestlessGhostWorkerHandler;

public class RestlessGhostMission extends Mission {

    public static final int RESTLESS_GHOST_VARP = 107;
    private final RestlessGhostWorkerHandler handler = new RestlessGhostWorkerHandler();

    public RestlessGhostMission(SPXScript script) {
        super(script);
    }

    @Override
    public String getMissionName() {
        return "Restless Ghost";
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
        return RestlessGhostState.isInVarp(RestlessGhostState.COMPLETE);
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

