package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.survival_expert;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class MakeFire extends TIWorker {

    private final Predicate<Item> LOGS = a -> a.getName().equals("Logs");
    private final Predicate<Item> TINDERBOX = a -> a.getName().equals("Tinderbox");

    public MakeFire(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.contains(LOGS);
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        if (Players.getLocal().getAnimation() != -1)
            return;

        final SceneObject FIRE_POSITION = SceneObjects.getFirstAt(Players.getLocal().getPosition());
        if (FIRE_POSITION == null || !FIRE_POSITION.getName().equals("Fire")) {
            if (Inventory.use(TINDERBOX, Inventory.getFirst("Logs")))
                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
        } else {
            Movement.walkTo(Players.getLocal().getPosition().translate(Random.nextInt(-1, 1), Random.nextInt(-1, 1)));
        }
    }

    @Override
    public String toString() {
        return "[SURVIVAL EXPERT]: Making fire";
    }
}

