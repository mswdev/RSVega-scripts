package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.mining_instructor;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class SmeltOre extends TI_Worker {

    private final Predicate<Item> TIN_ORE = a -> a.getName().equals("Tin ore");
    private final Predicate<Item> BRONZE_BAR = a -> a.getName().equals("Bronze bar");
    private final Predicate<SceneObject> FURNACE = a -> a.getName().equals("Furnace");

    public SmeltOre(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final SceneObject OBJECT = SceneObjects.getNearest(FURNACE);
        if (OBJECT == null)
            return;

        if (Inventory.use(TIN_ORE, OBJECT) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || OBJECT.distance() <= 1, 3500))
            Time.sleepUntil(() -> Inventory.contains(BRONZE_BAR), 3500);
    }

    @Override
    public String toString() {
        return "[MINING GUIDE]: Smelting bar";
    }
}

