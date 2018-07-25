package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.survival_expert;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class ChopTree extends TI_Worker {

    private final Predicate<Item> LOGS = a -> a.getName().equals("Logs");
    private final Predicate<SceneObject> TREE = a -> a.getName().equals("Tree");

    public ChopTree(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return !Inventory.contains(LOGS);
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final SceneObject OBJECT = SceneObjects.getNearest(TREE);
        if (OBJECT == null)
            return;

        if (mission.interactWithHint() && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || OBJECT.distance() <= 1, 3500))
            Time.sleepUntil(() -> Inventory.contains(LOGS), 8500);
    }

    @Override
    public String toString() {
        return "[SURVIVAL EXPERT]: Chopping tree";
    }
}

