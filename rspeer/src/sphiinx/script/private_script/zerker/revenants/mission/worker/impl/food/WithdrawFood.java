package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.food;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.util.function.Predicate;

public class WithdrawFood extends Worker<RevenantMission> {

    public static Predicate<Item> FOOD = a -> a.getName().equals("Shark");
    private static final WithdrawItemWorker WITHDRAW_FOOD = new WithdrawItemWorker(FOOD, 24);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        WITHDRAW_FOOD.work();
        mission.can_end = WITHDRAW_FOOD.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing food";
    }
}

