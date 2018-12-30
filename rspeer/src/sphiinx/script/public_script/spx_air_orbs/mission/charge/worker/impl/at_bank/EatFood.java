package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;

import java.util.function.Predicate;

public class EatFood extends Worker {

    public final Predicate<Item> item = a -> a.getName().equals("Jug of wine");
    private final ItemWorker eat_food = new ItemWorker(item);
    private final AirOrbChargeMission mission;

    public EatFood(AirOrbChargeMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        eat_food.work();
        mission.can_end = eat_food.itemNotFound();
    }

    @Override
    public String toString() {
        return "Eating food.";
    }
}

