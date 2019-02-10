package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;

public class Logout extends Worker {

    private final TutorialIslandMission mission;

    public Logout(TutorialIslandMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
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
        return "Logging out.";
    }
}

