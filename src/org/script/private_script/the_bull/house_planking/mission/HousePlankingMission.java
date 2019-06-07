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
import org.script.private_script.the_bull.house_planking.mission.worker.HousePlankingWorkerHandler;

public class HousePlankingMission extends Mission implements ItemManagement {

    public static final int[] RING_OF_WEALTH_IDS = new int[]{
            2572,
            11988,
            11986,
            11984,
            11982,
            11980
    };
    private final HousePlankingWorkerHandler workerHandler = new HousePlankingWorkerHandler(this);
    private final LogType logType;
    private final int quantity;
    private final int PLANK_ID = 960;
    private final int OAK_PLANK_ID = 8778;
    private final int MAHOGANY_PLANK_ID = 8782;
    private final int TEAK_PLANK_ID = 8780;
    public boolean shouldEnd;

    public HousePlankingMission(SPXScript script, LogType logType, int quantity) {
        super(script);
        this.logType = logType;
        this.quantity = quantity;
    }

    @Override
    public String getMissionName() {
        return "[P-SPX] House Planking";
    }

    @Override
    public String getWorkerName() {
        final Worker c = workerHandler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        final Worker c = workerHandler.getCurrent();
        return c == null ? "Waiting for worker." : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean shouldEnd() {
        return shouldEnd;
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public int execute() {
        if (Dialog.canContinue())
            Dialog.processContinue();


        workerHandler.work();
        return 150;
    }

    @Override
    public ItemManagementEntry[] itemsToBuy() {
        return new ItemManagementEntry[]{
                new ItemManagementEntry(this, RING_OF_WEALTH_IDS[5], 5, () -> getScript().getBankCache().getCache().getOrDefault(RING_OF_WEALTH_IDS[1], 0) <= 0 && getScript().getBankCache().getCache().getOrDefault(RING_OF_WEALTH_IDS[2], 0) <= 0 && getScript().getBankCache().getCache().getOrDefault(RING_OF_WEALTH_IDS[3], 0) <= 0 && getScript().getBankCache().getCache().getOrDefault(RING_OF_WEALTH_IDS[4], 0) <= 0 && getScript().getBankCache().getCache().getOrDefault(RING_OF_WEALTH_IDS[5], 0) <= 0 && Inventory.getCount(RING_OF_WEALTH_IDS[5] + 1) <= 0),
                new ItemManagementEntry(this, ItemManagementTracker.GOLD_ID, getRequiredGold(), () -> Inventory.getCount(true, ItemManagementTracker.GOLD_ID) < getRequiredGold()),
                new ItemManagementEntry(this, getLogType().getItemId(), getQuantity(), () -> getScript().getBankCache().getCache().getOrDefault(getLogType().getItemId(), 0) <= 0 && Inventory.getCount(true, getLogType().getItemId()) <= 0 && Inventory.getCount(getLogType().getNotedItemId()) <= 0 && !House.isInside() && !shouldEnd),
                new ItemManagementEntry(this, RuneType.LAW.getItemId(), ((getQuantity()) / 24) * 2 + 1, () -> getScript().getBankCache().getCache().getOrDefault(RuneType.LAW.getItemId(), 0) <= 0 && Inventory.getCount(true, RuneType.LAW.getItemId()) <= 0 && !shouldEnd),
                new ItemManagementEntry(this, RuneType.EARTH.getItemId(), ((getQuantity()) / 24) * 2 + 1, () -> getScript().getBankCache().getCache().getOrDefault(RuneType.EARTH.getItemId(), 0) <= 0 && Inventory.getCount(true, RuneType.EARTH.getItemId()) <= 0 && !shouldEnd)
        };
    }

    @Override
    public int[] itemsToSell() {
        return new int[]{
                PLANK_ID,
                OAK_PLANK_ID,
                MAHOGANY_PLANK_ID,
                TEAK_PLANK_ID,
                RING_OF_WEALTH_IDS[0],
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
        return logType;
    }

    private int getQuantity() {
        return quantity;
    }

    private int getRequiredGold() {
        final int amountOfLogs = Inventory.getCount(true, getLogType().getName());
        final int butlerPayment = ((amountOfLogs / 24) / 8) * 10000;
        int sawmillCost = 0;
        switch (getLogType()) {
            case LOGS:
                sawmillCost = PlankType.PLANK.getSawmillCost() * amountOfLogs;
                break;
            case OAK:
                sawmillCost = PlankType.OAK.getSawmillCost() * amountOfLogs;
                break;
            case TEAK:
                sawmillCost = PlankType.TEAK.getSawmillCost() * amountOfLogs;
                break;
            case MAHOGANY:
                sawmillCost = PlankType.MAHOGANY.getSawmillCost() * amountOfLogs;
                break;
            default:
                sawmillCost = PlankType.OAK.getSawmillCost() * amountOfLogs;
                break;
        }

        return sawmillCost + butlerPayment;
    }
}

