package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class Logout extends TIWorker {


    public Logout(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_end_logout)
            return false;

        return Game.getState() != Game.STATE_CREDENTIALS_SCREEN;
    }

    @Override
    public void work() {
        if (Game.logout())
            Time.sleepUntil(() -> !Game.isLoggedIn(), 1500);
    }

    @Override
    public String toString() {
        return "[END]: Logging out";
    }
}

