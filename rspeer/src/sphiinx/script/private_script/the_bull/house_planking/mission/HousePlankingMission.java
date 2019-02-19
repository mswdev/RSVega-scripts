package sphiinx.script.private_script.the_bull.house_planking.mission;

import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.api.game.skills.magic.RuneType;
import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.api.script.framework.item_management.ItemManagement;
import sphiinx.api.script.framework.item_management.ItemManagementEntry;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.private_script.the_bull.house_planking.Main;
import sphiinx.script.private_script.the_bull.house_planking.mission.worker.HousePlankingWorkerHandler;

public class HousePlankingMission extends Mission implements ItemManagement {

    public boolean can_end;
    private final HousePlankingWorkerHandler worker_handler = new HousePlankingWorkerHandler(this);
    private final int OAK_PLANK_ID = 8788;

    public HousePlankingMission(SPXScript script) {
        super(script);
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
        return can_end;
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
        // [TODO - 2/18/2019]: Convert price to use a price modifier and API so you don't have to specify.
        return new ItemManagementEntry[]{
                new ItemManagementEntry(this, Main.ARGS.LOG_TYPE1.getItemID(), Main.ARGS.LOG_TYPE1_QUANTITY, Main.ARGS.LOG_TYPE1_PRICE, () -> getScript().bank_cache.get().getOrDefault(Main.ARGS.LOG_TYPE1.getItemID(), 0) <= 0 && Inventory.getCount(true, Main.ARGS.LOG_TYPE1.getItemID() + 1) <= 0),
                new ItemManagementEntry(this, RuneType.LAW.getItemID(), 1251, 250, () -> getScript().bank_cache.get().getOrDefault(RuneType.LAW.getItemID(), 0) <= 0 && Inventory.getCount(true, RuneType.LAW.getItemID()) <= 0),
                new ItemManagementEntry(this, RuneType.EARTH.getItemID(), 1251, 10, () -> getScript().bank_cache.get().getOrDefault(RuneType.EARTH.getItemID(), 0) <= 0 && Inventory.getCount(true, RuneType.EARTH.getItemID()) <= 0),
        };
    }

    @Override
    public int[] itemsToSell() {
        return new int[]{
                OAK_PLANK_ID,
        };
    }
}

