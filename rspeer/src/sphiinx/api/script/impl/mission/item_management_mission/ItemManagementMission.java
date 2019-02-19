package sphiinx.api.script.impl.mission.item_management_mission;

import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.api.script.framework.item_management.ItemManagementEntry;
import sphiinx.api.script.framework.item_management.ItemManagementTracker;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.mission.item_management_mission.worker.ItemManagementWorkerHandler;

public class ItemManagementMission extends Mission {

    private final ItemManagementWorkerHandler worker_handler;
    public final ItemManagementEntry item_management_entry;
    public final ItemManagementTracker item_management_tracker;
    public final int[] items_to_sell;
    public boolean has_put_in_offer;
    public boolean has_withdrawn_sellables;
    public boolean should_end;

    public ItemManagementMission(SPXScript script, ItemManagementEntry item_management_entry, ItemManagementTracker item_management_tracker, int[] items_to_sell) {
        super(script);
        this.item_management_entry = item_management_entry;
        this.item_management_tracker = item_management_tracker;
        this.items_to_sell = items_to_sell;
        worker_handler = new ItemManagementWorkerHandler(this);
    }

    @Override
    public String getMissionName() {
        return "Item Management";
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
        return should_end;
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
}
