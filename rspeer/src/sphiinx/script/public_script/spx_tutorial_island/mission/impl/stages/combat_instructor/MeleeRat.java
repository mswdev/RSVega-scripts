package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.combat_instructor;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class MeleeRat extends TI_Worker {

    public MeleeRat(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Players.getLocal().getTargetIndex() != -1)
            return;

        if (mission.interactWithHint() && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving(), 3500))
            Time.sleepUntil(() -> Players.getLocal().getTargetIndex() != -1, 1500);
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Meleeing rat";
    }
}

