package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.food;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

public class EatFood extends Worker<RevenantMission> {

    private static final ItemActionWorker eat_food = new ItemActionWorker(WithdrawFood.FOOD);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        eat_food.work();
    }

    @Override
    public String toString() {
        return "Eating food";
    }
}

