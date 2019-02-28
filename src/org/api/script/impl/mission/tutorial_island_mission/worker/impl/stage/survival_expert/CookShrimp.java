package org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.survival_expert;

import org.api.game.skills.fishing.FishType;
import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.function.Predicate;

public class CookShrimp extends Worker {

    private static final Predicate<SceneObject> FIRE = a -> a.getName().equals("Fire");

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final SceneObject fire_object = SceneObjects.getNearest(FIRE);
        if (fire_object == null)
            return;

        if (Inventory.use(a -> a.getName().equals(FishType.SHRIMP.getName()), fire_object) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 3500))
            Time.sleepUntil(() -> !Inventory.contains(FishType.SHRIMP.getName()), 6500);
    }

    @Override
    public String toString() {
        return "Cooking shrimp.";
    }
}

