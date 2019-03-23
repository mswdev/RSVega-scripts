package org.api.script.impl.mission.blast_furnace_mission;

import org.api.game.skills.smithing.BarType;
import org.api.script.SPXScript;
import org.api.script.framework.goal.GoalList;
import org.api.script.framework.goal.impl.InfiniteGoal;
import org.api.script.framework.mission.Mission;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.blast_furnace_mission.worker.BlastFurnaceWorkerHandler;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.Movement;

import java.util.function.Predicate;

public class BlastFurnaceMission extends Mission {

    public static final Predicate<Item> COINS = a -> a.getName().equals("Coins");
    public static BarType bar_type = BarType.STEEL;
    private final BlastFurnaceWorkerHandler worker_handler = new BlastFurnaceWorkerHandler(this);
    public boolean is_coal_bag_empty = true;
    public boolean is_smelting;
    public boolean should_end;

    public BlastFurnaceMission(SPXScript script) {
        super(script);
    }

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

        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 20)
            Movement.toggleRun(true);

        worker_handler.work();
        return 100;
    }
}

