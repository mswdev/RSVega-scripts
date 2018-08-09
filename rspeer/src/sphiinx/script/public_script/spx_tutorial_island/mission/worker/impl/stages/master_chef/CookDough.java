package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.master_chef;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class CookDough extends TIWorker {

    private final Predicate<Item> BREAD_DOUGH = a -> a.getName().equals("Bread dough");
    private final Predicate<SceneObject> RANGE = a -> a.getName().equals("Range");

    public CookDough(TIMission mission) {
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

        final SceneObject OBJECT = SceneObjects.getNearest(RANGE);
        if (OBJECT == null)
            return;

        if (Inventory.use(BREAD_DOUGH, OBJECT) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || OBJECT.distance() <= 1, 3500))
            Time.sleepUntil(() -> Inventory.contains("Bread"), 3500);
    }

    @Override
    public String toString() {
        return "[MASTER CHEF]: Cooking bread dough";
    }
}

