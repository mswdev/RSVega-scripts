package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.OpenBankWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.BlastFurnaceWorker;

import java.util.function.Predicate;

public class DrinkStamina extends BlastFurnaceWorker {

    public static final Predicate<Item> stamina = a -> a.getName().contains("Stamina");
    public static final Predicate<Item> vial = a -> a.getName().equals("Vial");
    private final ItemActionWorker drink_stamina_potion;
    private final OpenBankWorker open_bank;
    public boolean out_of_stamina;

    public DrinkStamina(BlastFurnaceMission mission) {
        super(mission);
        drink_stamina_potion = new ItemActionWorker(stamina);
        open_bank = new OpenBankWorker(false);
    }

    @Override
    public void work() {
        // [TODO - 2018-10-26]: Remove this once the deposit worker is added.
        if (Movement.isStaminaEnhancementActive() && Inventory.contains(stamina)) {
            if (Bank.isOpen()) {
                if (Inventory.contains(vial))
                    if (Bank.depositAll(vial))
                        Time.sleepUntil(() -> !Inventory.contains(vial), 1500);

                if (Bank.depositAll(stamina))
                    Time.sleepUntil(() -> !Inventory.contains(stamina), 1500);
            } else {
                open_bank.work();
            }
        } else {
            drink_stamina_potion.work();
            out_of_stamina = drink_stamina_potion.itemNotFound();
        }
    }

    @Override
    public String toString() {
        return "Drinking Stamina potion";
    }
}

