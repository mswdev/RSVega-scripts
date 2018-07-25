package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.mining_instructor;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class MiningInstructorDialogue extends TI_Worker {

    private final Position MINING_INSTRUCTOR = new Position(3079, 9515, 0);

    public MiningInstructorDialogue(TI_Mission mission) {
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
            if (mission.interactWithHint())
                Time.sleepUntil(Interfaces::isDialogOpen, 1500);
        } else {
            Movement.walkTo(MINING_INSTRUCTOR);
        }
    }

    @Override
    public String toString() {
        return "[MINING GUIDE]: Completing dialogue";
    }
}

