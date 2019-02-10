package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.blast_furnace_mission;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.goal.GoalList;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.game.skills.smithing.BarType;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.blast_furnace_mission.worker.BlastFurnaceWorkerHandler;

import java.util.function.Predicate;

public class BlastFurnaceMission extends Mission {

    public static final Predicate<Item> COINS = a -> a.getName().equals("Coins");
    public static BarType bar_type = BarType.STEEL;
    public boolean is_coal_bag_empty = true;
    public boolean is_smelting;
    public boolean can_end;
    private final BlastFurnaceWorkerHandler worker_handler = new BlastFurnaceWorkerHandler(this);

    @Override
    public String getMissionName() {
        return "AIO Blast Furnace";
    }

    @Override
    public String getWorkerName() {
        final Worker c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        final Worker c = worker_handler.getCurrent();
        return c == null ? "Waiting for worker" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
        return can_end;
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public int execute() {
        //todo Temporary until they fix the method to continue the chat box.
        if (Dialog.canContinue())
            Dialog.processContinue();

        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 20)
            Movement.toggleRun(true);

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
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }
}

