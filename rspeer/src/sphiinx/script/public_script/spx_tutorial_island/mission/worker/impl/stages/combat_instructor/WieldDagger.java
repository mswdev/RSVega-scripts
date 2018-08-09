package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.combat_instructor;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class WieldDagger extends TIWorker {

    private final Predicate<Item> BRONZE_DAGGER = a -> a.getName().equals("Bronze dagger");
    private final Predicate<String> WIELD = a -> a.equals("Wield");

    public WieldDagger(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        final Item DAGGER = Inventory.getFirst(BRONZE_DAGGER);
        if (DAGGER == null)
            return;

        if (DAGGER.interact(WIELD))
            Time.sleepUntil(() -> Equipment.contains(BRONZE_DAGGER.toString()), 1500);
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Wielding dagger";
    }
}

