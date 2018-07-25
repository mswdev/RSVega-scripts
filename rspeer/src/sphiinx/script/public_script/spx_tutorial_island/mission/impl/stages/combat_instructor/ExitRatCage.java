package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.combat_instructor;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class ExitRatCage extends TI_Worker {

    private final Predicate<SceneObject> GATE = a -> a.getName().equals("Gate");
    private final Predicate<Npc> COMBAT_INSTRUCTOR = a -> a.getName().equals("Combat Instructor");
    private final Predicate<String> OPEN = a -> a.equals("Open");

    public ExitRatCage(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        final Npc NPC = Npcs.getFirst(COMBAT_INSTRUCTOR);
        return NPC != null && !NPC.isPositionInteractable();
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        final SceneObject OBJECT = SceneObjects.getNearest(GATE);
        if (OBJECT == null)
            return;

        if (OBJECT.interact(OPEN) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || OBJECT.distance() <= 1, 3500))
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Exiting rat cage";
    }
}

