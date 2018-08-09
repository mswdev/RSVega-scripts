package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.combat_instructor;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class RangeRat extends TIWorker {

    private final Predicate<Item> SHORTBOW = a -> a.getName().equals("Shortbow");
    private final Predicate<Item> BRONZE_ARROW = a -> a.getName().equals("Bronze arrow");
    private final Predicate<String> WIELD = a -> a.equals("Wield");

    public RangeRat(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Players.getLocal().getTargetIndex() != -1)
            return;

        if (!Inventory.contains(SHORTBOW) && !Inventory.contains(BRONZE_ARROW)) {
            if (mission.interactWithHint(mission.getHintNPC()))
                Time.sleepUntil(() -> Players.getLocal().getTargetIndex() != -1, 1500);
        } else {
            final Item BOW = Inventory.getFirst(SHORTBOW);
            final Item ARROW = Inventory.getFirst(BRONZE_ARROW);

            if (BOW != null) {
                if (BOW.interact(WIELD))
                    Time.sleepUntil(() -> Equipment.isOccupied(EquipmentSlot.MAINHAND), 1500);
            }

            if (ARROW != null) {
                if (ARROW.interact(WIELD))
                    Time.sleepUntil(() -> Equipment.isOccupied(EquipmentSlot.QUIVER), 1500);
            }
        }
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Ranging rat";
    }
}

