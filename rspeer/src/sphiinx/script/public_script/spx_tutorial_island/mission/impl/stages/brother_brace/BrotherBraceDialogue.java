package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.brother_brace;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class BrotherBraceDialogue extends TI_Worker {

    private final Predicate<SceneObject> LARGE_DOOR = a -> a.getName().equals("Large door");
    private final Position BROTHER_BRACE = new Position(3127, 3107, 0);

    public BrotherBraceDialogue(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving())
            return;

        final Npc NPC = mission.getHintNPC();

        if (NPC != null) {
            if (NPC.isPositionInteractable()) {
                if (mission.interactWithHint())
                    Time.sleepUntil(Interfaces::isDialogOpen, 1500);
            } else {
                final SceneObject OBJECT = SceneObjects.getNearest(LARGE_DOOR);
                if (OBJECT == null)
                    return;

                if (OBJECT.interact("Open") && Time.sleepUntil(() -> OBJECT.distance() <= 1, 3500));
                    Time.sleepUntil(NPC::isPositionInteractable, 1500);
            }
        } else {
            Movement.walkTo(BROTHER_BRACE);
        }
    }

    @Override
    public String toString() {
        return "[BROTHER BRACE]: Completing dialogue";
    }
}

