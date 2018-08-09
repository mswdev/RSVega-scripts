package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.combat_instructor;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class ExitRatCage extends TIWorker {

    private final Predicate<SceneObject> GATE = a -> a.getName().equals("Gate");
    private final Predicate<Npc> COMBAT_INSTRUCTOR = a -> a.getName().equals("Combat Instructor");
    private final Predicate<String> OPEN = a -> a.equals("Open");

    public ExitRatCage(TIMission mission) {
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

        final Npc NPC = Npcs.getFirst(COMBAT_INSTRUCTOR);
        if (NPC == null)
            return;

        if (Movement.walkTo(NPC))
            Time.sleepUntil(NPC::isPositionInteractable, 1500);
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Exiting rat cage";
    }
}

