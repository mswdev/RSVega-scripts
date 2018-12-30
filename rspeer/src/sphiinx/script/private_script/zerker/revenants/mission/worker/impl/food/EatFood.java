package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.food;

import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;

public class EatFood extends Worker {

    private static final ItemWorker eat_food = new ItemWorker(WithdrawFood.FOOD);

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
        return "Eating food.";
    }
}

