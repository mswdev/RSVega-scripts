package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.master_chef;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class MakeDough extends TI_Worker {

    private final Predicate<Item> FLOUR = a -> a.getName().equals("Pot of flour");
    private final Predicate<Item> DOUGH = a -> a.getName().equals("Bread dough");

    public MakeDough(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Inventory.use(FLOUR, Inventory.getFirst("Bucket of water")))
            Time.sleepUntil(() -> Inventory.contains(DOUGH), 1500);
    }

    @Override
    public String toString() {
        return "[MASTER CHEF]: Making bread dough";
    }
}

