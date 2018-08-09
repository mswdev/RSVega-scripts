package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.survival_expert;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class CookShrimp extends TIWorker {

    private final Predicate<Item> RAW_SHRIMPS = a -> a.getName().equals("Raw shrimps");
    private final Predicate<Item> BURNT_SHRIMP = a -> a.getName().equals("Burnt shrimp");
    private final Predicate<Item> SHRIMPS = a -> a.getName().equals("Shrimps");
    private final Predicate<SceneObject> FIRE = a -> a.getName().equals("Fire");

    public CookShrimp(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.getCount(RAW_SHRIMPS) > 0;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final SceneObject OBJECT = SceneObjects.getNearest(FIRE);
        if (OBJECT == null)
            return;

        if (Inventory.use(RAW_SHRIMPS, OBJECT) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || OBJECT.distance() <= 1, 3500))
            Time.sleepUntil(() -> Inventory.contains(BURNT_SHRIMP) || Inventory.contains(SHRIMPS), 8500);
    }

    @Override
    public String toString() {
        return "[SURVIVAL EXPERT]: Cooking shrimp";
    }
}

