package sphiinx.script.testing.data_testing.data_test_mission_2.mission;

import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.api.script.framework.item_management.ItemManagement;
import sphiinx.api.script.framework.item_management.ItemManagementEntry;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.testing.data_testing.data_test_mission_2.mission.worker.DataTestWorkerHandler;

public class DataTestMission extends Mission implements ItemManagement {

    private final DataTestWorkerHandler worker_handler;


    public DataTestMission(SPXScript script) {
        super(script);
        worker_handler = new DataTestWorkerHandler();
    }

    @Override
    public String getMissionName() {
        return "Data Test Mission 2";
    }

    @Override
    public String getWorkerName() {
        Worker c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = worker_handler.getCurrent();
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
        worker_handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {
    }

    @Override
    public void onMissionEnd() {
    }

    @Override
    public ItemManagementEntry[] itemsToBuy() {
        return new ItemManagementEntry[0];
    }

    @Override
    public int[] itemsToSell() {
        return new int[0];
    }

    @Override
    public double sellPriceModifier() {
        return 0;
    }

    @Override
    public double buyPriceModifier() {
        return 0;
    }
}
