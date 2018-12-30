package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stage.survival_expert;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.game.skills.firemaking.LogType;

import java.util.function.Predicate;

public class LightFire extends Worker {

    private static final Predicate<Item> TINDERBOX = a -> a.getName().equals("Tinderbox");

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (Players.getLocal().getAnimation() != -1)
            return;

        final Item item = Inventory.getFirst(LogType.LOGS.getName());
        if (item == null)
            return;

        final SceneObject fire_position = SceneObjects.getFirstAt(Players.getLocal().getPosition());
        if (fire_position == null || !fire_position.getName().equals("Fire")) {
            if (Inventory.use(TINDERBOX, item))
                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
        } else {
            if (Movement.walkTo(Players.getLocal().getPosition().translate(Random.nextInt(-1, 1), Random.nextInt(-1, 1))))
                Time.sleepUntil(() -> Players.getLocal().isMoving(), 1500);
        }
    }

    @Override
    public String toString() {
        return "Lighting fire.";
    }
}

