package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

import java.util.function.Predicate;

public class DrinkStamina extends AirOrbChargeWorker {

    public boolean out_of_stamina;
    private final Predicate<Item> stamina = a -> a.getName().contains("Stamina");
    private final ItemActionWorker drink_stamina_potion;

    public DrinkStamina(AirOrbChargeMission mission) {
        super(mission);
        drink_stamina_potion = new ItemActionWorker<>(stamina);
    }

    @Override
    public void work() {
        drink_stamina_potion.work();
        out_of_stamina = drink_stamina_potion.itemNotFound();
    }

    @Override
    public String toString() {
        return "Drinking Stamina potion";
    }
}

