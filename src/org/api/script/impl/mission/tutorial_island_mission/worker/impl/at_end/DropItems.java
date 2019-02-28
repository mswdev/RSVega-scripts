package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.function.Predicate;

public class DropItems extends Worker {

    private final Predicate<String> DROP = a -> a.equals("Drop");

    @Override
    public boolean needsRepeat() {
        return Inventory.getCount() > 0;
    }

    @Override
    public void work() {
        final Item item = Inventory.getItems()[0];
        if (item == null)
            return;

        final int CACHE = Inventory.getCount();
        if (item.interact(DROP))
            Time.sleepUntil(() -> Inventory.getCount() < CACHE, 1500);
    }

    @Override
    public String toString() {
        return "Dropping items.";
    }
}

