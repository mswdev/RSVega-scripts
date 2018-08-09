package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.quest_guide;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class PerformEmote extends TIWorker {


    private final int INTER_MASTER = 216, CHILD = 1;
    private final int EMOTE_MIN = 0, EMOTE_MAX = 12;

    public PerformEmote(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        final InterfaceComponent EMOTE = Interfaces.getComponent(INTER_MASTER, CHILD, Random.nextInt(EMOTE_MIN, EMOTE_MAX));
        if (EMOTE == null)
            return;

        if ((EMOTE).interact(mission.DEFAULT_ACTION))
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
    }

    @Override
    public String toString() {
        return "[QUEST GUIDE]: Performing emote";
    }
}

