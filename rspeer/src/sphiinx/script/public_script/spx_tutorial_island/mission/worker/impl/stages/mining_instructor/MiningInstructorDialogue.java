package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.mining_instructor;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class MiningInstructorDialogue extends TIWorker {

    private final Position MINING_INSTRUCTOR = new Position(3079, 9515, 0);

    public MiningInstructorDialogue(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        final Npc NPC = mission.getHintNPC();
        if (NPC != null) {
            if (mission.interactWithHint(mission.getHintNPC()))
                Time.sleepUntil(Interfaces::isDialogOpen, 1500);
        } else {
            if (Movement.walkTo(MINING_INSTRUCTOR))
                Time.sleepUntil(() -> mission.getHintNPC() != null, 1500);
        }
    }

    @Override
    public String toString() {
        return "[MINING GUIDE]: Completing dialogue";
    }
}

