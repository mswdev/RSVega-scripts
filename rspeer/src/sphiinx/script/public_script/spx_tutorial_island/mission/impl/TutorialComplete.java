package sphiinx.script.public_script.spx_tutorial_island.mission.impl;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class TutorialComplete extends TI_Worker {

    public TutorialComplete(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return Game.isLoggedIn();
    }

    @Override
    public void work() {
        if (Game.logout() && Time.sleepUntil(() -> !Game.isLoggedIn(), 1500)) {
            mission.setNextAccount();
        }
    }

    @Override
    public String toString() {
        return "[GENERAL]: Completed tutorial island";
    }
}

