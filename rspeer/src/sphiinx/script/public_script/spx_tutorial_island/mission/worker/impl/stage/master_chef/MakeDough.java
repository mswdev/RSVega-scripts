package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stage.master_chef;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.mission.TutorialIslandMission;

import java.util.function.Predicate;

public class MakeDough extends Worker<TutorialIslandMission> {

    private static final Predicate<Item> FLOUR = a -> a.getName().equals("Pot of flour");
    private static final Predicate<Item> DOUGH = a -> a.getName().equals("Bread dough");
    private static final Predicate<Item> WATER = a -> a.getName().equals("Bucket of water");

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Inventory.use(FLOUR, Inventory.getFirst(WATER)))
            Time.sleepUntil(() -> Inventory.contains(DOUGH), 1500);
    }

    @Override
    public String toString() {
        return "Making bread dough";
    }
}

