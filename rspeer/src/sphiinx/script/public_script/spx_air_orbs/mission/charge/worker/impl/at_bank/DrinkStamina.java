package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;

import java.util.function.Predicate;

public class DrinkStamina extends Worker {

    public boolean out_of_stamina;
    private final Predicate<Item> stamina = a -> a.getName().contains("Stamina");
    private final ItemWorker drink_stamina_potion = new ItemWorker(stamina);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        drink_stamina_potion.work();
        out_of_stamina = drink_stamina_potion.itemNotFound();
    }

    @Override
    public String toString() {
        return "Drinking Stamina potion.";
    }
}

