package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.combat_instructor;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class CombatInstructorDialogue extends TIWorker {


    public CombatInstructorDialogue(TIMission mission) {
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
        if (NPC == null)
            return;

        if (NPC.isPositionInteractable()) {
            if (mission.interactWithHint(mission.getHintNPC()))
                Time.sleepUntil(Interfaces::isDialogOpen, 1500);
        } else {
            if (Movement.walkTo(NPC))
                Time.sleepUntil(NPC::isPositionInteractable, 1500);
        }
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Completing dialogue";
    }
}

