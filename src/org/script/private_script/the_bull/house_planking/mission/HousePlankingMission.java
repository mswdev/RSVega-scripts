package org.script.private_script.the_bull.house_planking.mission;

import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.House;
import org.api.game.skills.firemaking.LogType;
import org.api.game.skills.magic.RuneType;
import org.api.script.SPXScript;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.goal.impl.InfiniteGoal;
import org.api.script.framework.item_management.ItemManagement;
import org.api.script.framework.item_management.ItemManagementEntry;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.worker.Worker;
import org.script.private_script.the_bull.house_planking.mission.worker.HousePlankingWorkerHandler;

public class HousePlankingMission extends Mission implements ItemManagement {

    public boolean should_end;
    private final HousePlankingWorkerHandler worker_handler = new HousePlankingWorkerHandler(this);
    private final LogType log_type;
    private final int quantity;

    private final int PLANK_ID = 960;
    private final int OAK_PLANK_ID = 8778;
    private final int MAHOGANY_PLANK_ID = 8782;
    private final int TEAK_PLANK_ID = 8780;

    public HousePlankingMission(SPXScript script, LogType log_type, int quantity) {
        super(script);
        this.log_type = log_type;
        this.quantity = quantity;
    }

    @Override
    public String getMissionName() {
        return "[P-SPX] House Planking";
    }

    @Override
    public String getWorkerName() {
        final Worker c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        final Worker c = worker_handler.getCurrent();
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
        // [TODO - 2018-11-28]: Temporary until they fix the method to continue the chat box.
        if (Dialog.canContinue())
            Dialog.processContinue();

        worker_handler.work();
        return 150;
    }

    @Override
    public void onMissionStart() {

    }

    @Override
    public void onMissionEnd() {

    }

    @Override
    public ItemManagementEntry[] itemsToBuy() {
        return new ItemManagementEntry[]{
                new ItemManagementEntry(this, getLogType().getItemID(), getQuantity(), () -> getScript().bank_cache.get().getOrDefault(getLogType().getItemID(), 0) <= 0 && Inventory.getCount(true, getLogType().getItemID()) <= 0 && Inventory.getCount(getLogType().getNotedItemID()) <= 0 &&!House.isInside() && !should_end),
                new ItemManagementEntry(this, RuneType.LAW.getItemID(), ((getQuantity()) / 24) * 2 + 1, () -> getScript().bank_cache.get().getOrDefault(RuneType.LAW.getItemID(), 0) <= 0 && Inventory.getCount(true, RuneType.LAW.getItemID()) <= 0 && !should_end),
                new ItemManagementEntry(this, RuneType.EARTH.getItemID(), ((getQuantity()) / 24) * 2 + 1, () -> getScript().bank_cache.get().getOrDefault(RuneType.EARTH.getItemID(), 0) <= 0 && Inventory.getCount(true, RuneType.EARTH.getItemID()) <= 0 && !should_end),
        };
    }

    @Override
    public int[] itemsToSell() {
        return new int[]{
                PLANK_ID,
                OAK_PLANK_ID,
                MAHOGANY_PLANK_ID,
                TEAK_PLANK_ID
        };
    }

    @Override
    public double sellPriceModifier() {
        return 0.8;
    }

    @Override
    public double buyPriceModifier() {
        return 1.5;
    }

    public LogType getLogType() {
        return log_type;
    }

    public int getQuantity() {
        return quantity;
    }
}

