package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class StayLoggedIn extends TIWorker {


    public StayLoggedIn(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return Vars.get().at_end_stay_logged_in;
    }

    @Override
    public void work() {
    }

    @Override
    public String toString() {
        return "[END]: Staying logged in";
    }
}

