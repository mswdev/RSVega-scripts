package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

import java.util.function.Predicate;

public class EatFood extends AirOrbChargeWorker {

    public final Predicate<Item> item = a -> a.getName().equals("Jug of wine");
    private final ItemActionWorker eat_food;

    public EatFood(AirOrbChargeMission mission) {
        super(mission);
        eat_food = new ItemActionWorker(item);
    }

    @Override
    public void work() {
        eat_food.work();
        mission.can_end = eat_food.itemNotFound();
    }

    @Override
    public String toString() {
        return "Eating food";
    }
}

