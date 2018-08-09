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

public class EnterRatCage extends TIWorker {

    private final Predicate<SceneObject> GATE = a -> a.getName().equals("Gate");
    private final Predicate<String> OPEN = a -> a.equals("Open");

    public EnterRatCage(TIMission mission) {
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

        final Npc NPC = Npcs.getNearest("Giant rat");
        if (NPC == null)
            return;

        if (Movement.walkTo(NPC))
            Time.sleepUntil(NPC::isPositionInteractable, 3500);

    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Entering rat cage";
    }
}

