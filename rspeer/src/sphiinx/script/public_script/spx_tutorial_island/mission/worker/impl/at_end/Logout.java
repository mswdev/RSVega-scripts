package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.mission.TutorialIslandMission;

public class Logout extends Worker<TutorialIslandMission> {

    public Logout(TutorialIslandMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Game.logout()) {
            if (Time.sleepUntil(() -> !Game.isLoggedIn(), 6500)) {
                mission.getScript().setAccount(null);
                mission.setShouldStop(true);
            }
        }
    }

    @Override
    public String toString() {
        return "Logging out";
    }
}

