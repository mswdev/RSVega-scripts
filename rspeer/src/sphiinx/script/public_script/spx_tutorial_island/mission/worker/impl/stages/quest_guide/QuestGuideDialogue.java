package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.quest_guide;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class QuestGuideDialogue extends TIWorker {


    public QuestGuideDialogue(TIMission mission) {
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

        if (mission.interactWithHint(mission.getHintNPC()))
            Time.sleepUntil(Interfaces::isDialogOpen, 1500);
    }

    @Override
    public String toString() {
        return "[QUEST GUIDE]: Completing dialogue";
    }
}

