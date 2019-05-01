package org.script.testing.data_testing.data_test_mission_2.mission;

import org.api.script.SPXScript;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.goal.impl.InfiniteGoal;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.worker.Worker;
import org.script.testing.data_testing.data_test_mission_2.mission.worker.DataTestWorkerHandler;

public class DataTestMission extends Mission {

    private final DataTestWorkerHandler workerHandler;


    public DataTestMission(SPXScript script) {
        super(script);
        workerHandler = new DataTestWorkerHandler();
    }

    @Override
    public String getMissionName() {
        return "Data Test Mission 2";
    }

    @Override
    public String getWorkerName() {
        Worker c = workerHandler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = workerHandler.getCurrent();
        return c == null ? "Waiting for worker." : c.toString();
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
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public int execute() {
        workerHandler.work();
        return 100;
    }
}
