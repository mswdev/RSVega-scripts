package org.script.private_script.the_bull.house_planking.mission;

import org.api.game.skills.contruction.PlankType;
import org.api.game.skills.firemaking.LogType;
import org.api.game.skills.magic.RuneType;
import org.api.script.SPXScript;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.goal.impl.InfiniteGoal;
import org.api.script.framework.item_management.ItemManagement;
import org.api.script.framework.item_management.ItemManagementEntry;
import org.api.script.framework.item_management.ItemManagementTracker;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.House;
import org.rspeer.ui.Log;
import org.script.private_script.the_bull.house_planking.mission.worker.HousePlankingWorkerHandler;

public class HousePlankingMission extends Mission implements ItemManagement {

    private final HousePlankingWorkerHandler worker_handler = new HousePlankingWorkerHandler(this);
    private final LogType log_type;
    private final int quantity;
    private final int PLANK_ID = 960;
    private final int OAK_PLANK_ID = 8778;
    private final int MAHOGANY_PLANK_ID = 8782;
    private final int TEAK_PLANK_ID = 8780;
    public boolean should_end;

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
                new ItemManagementEntry(this, ItemManagementTracker.GOLD_ID, getRequiredGold(), () -> Inventory.getCount(true, ItemManagementTracker.GOLD_ID) < getRequiredGold()),
                new ItemManagementEntry(this, getLogType().getItemID(), getQuantity(), () -> getScript().bank_cache.get().getOrDefault(getLogType().getItemID(), 0) <= 0 && Inventory.getCount(true, getLogType().getItemID()) <= 0 && Inventory.getCount(getLogType().getNotedItemID()) <= 0 && !House.isInside() && !should_end),
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

    private int getQuantity() {
        return quantity;
    }

    private int getRequiredGold() {
        final int amount_of_logs = Inventory.getCount(true, getLogType().getName());
        final int butler_payment = ((amount_of_logs / 24) / 8) * 10000;
        int sawmill_cost = 0;
        switch (getLogType()) {
            case LOGS:
                sawmill_cost = PlankType.PLANK.getSawmillCost() * amount_of_logs;
                break;
            case OAK:
                sawmill_cost = PlankType.OAK.getSawmillCost() * amount_of_logs;
                break;
            case TEAK:
                sawmill_cost = PlankType.TEAK.getSawmillCost() * amount_of_logs;
                break;
            case MAHOGANY:
                sawmill_cost = PlankType.MAHOGANY.getSawmillCost() * amount_of_logs;
                break;
        }

        return sawmill_cost + butler_payment;
    }
}

