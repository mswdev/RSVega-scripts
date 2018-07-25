package sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_end;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class DropItems extends TI_Worker {

    private final Predicate<String> DROP = a -> a.equals("Drop");

    public DropItems(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_end_drop_items)
            return false;

        return Inventory.getCount() > 0;
    }

    @Override
    public void work() {
        final Item[] ITEMS = Inventory.getItems();
        if (ITEMS == null)
            return;

        for (Item item : ITEMS) {
            if (item == null)
                continue;

            final int CACHE = Inventory.getCount();
            if (item.interact(DROP))
                Time.sleepUntil(() -> Inventory.getCount() < CACHE, 1500);
        }
    }

    @Override
    public String toString() {
        return "[END]: Dropping items";
    }
}

