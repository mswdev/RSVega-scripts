package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.combat_instructor;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class WieldSwordAndShield extends TI_Worker {

    private final Predicate<Item> BRONZE_SWORD = a -> a.getName().equals("Bronze sword");
    private final Predicate<Item> WOODEN_SHIELD = a -> a.getName().equals("Wooden shield");
    private final Predicate<String> WIELD = a -> a.equals("Wield");

    public WieldSwordAndShield(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        final Item SWORD = Inventory.getFirst(BRONZE_SWORD);
        final Item SHIELD = Inventory.getFirst(WOODEN_SHIELD);
        if (SWORD != null) {
            if (Inventory.getFirst(BRONZE_SWORD).interact(WIELD))
                Time.sleepUntil(() -> Equipment.isOccupied(EquipmentSlot.MAINHAND), 1500);
        }

        if (SHIELD != null) {
            if (Inventory.getFirst(WOODEN_SHIELD).interact(WIELD))
                Time.sleepUntil(() -> Equipment.isOccupied(EquipmentSlot.MAINHAND), 1500);
        }
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Wielding sword and shield";
    }
}

