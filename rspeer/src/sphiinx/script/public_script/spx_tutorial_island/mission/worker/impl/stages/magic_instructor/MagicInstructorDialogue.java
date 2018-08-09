package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.magic_instructor;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class MagicInstructorDialogue extends TIWorker {

    private final Position MAGIC_INSTRUCTOR = new Position(3141, 3087, 0);

    public MagicInstructorDialogue(TIMission mission) {
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

        if (Interfaces.isViewingChatOptions()) {
            if (Interfaces.getChatOptions().length <= 2) {
                if (Interfaces.processDialog(0))
                    Time.sleepUntil(Interfaces::isDialogProcessing, 1500);
            } else {
                if (Interfaces.processDialog(1))
                    Time.sleepUntil(Interfaces::isDialogProcessing, 1500);
            }
        } else if (NPC != null) {
            if (mission.interactWithHint(mission.getHintNPC()))
                Time.sleepUntil(Interfaces::isDialogOpen, 1500);
        } else {
            Movement.walkTo(MAGIC_INSTRUCTOR);
        }
    }

    @Override
    public String toString() {
        return "[MAGIC INSTRUCTOR]: Completing dialogue";
    }
}

