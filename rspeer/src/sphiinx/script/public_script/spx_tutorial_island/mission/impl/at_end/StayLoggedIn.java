package sphiinx.script.public_script.spx_tutorial_island.mission.impl.at_end;

import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

public class StayLoggedIn extends TI_Worker {


    public StayLoggedIn(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_end_stay_logged_in)
            return false;

        return true;
    }

    @Override
    public void work() {
    }

    @Override
    public String toString() {
        return "[END]: Staying logged in";
    }
}

