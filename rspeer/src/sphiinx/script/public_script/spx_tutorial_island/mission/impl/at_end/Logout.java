package sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_end;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class Logout extends TI_Worker {


    public Logout(TI_Mission mission) {
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

