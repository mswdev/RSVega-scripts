package sphiinx.script.public_script.spx_tutorial_island.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.worker.Worker;

public class StayLoggedIn extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
    }

    @Override
    public String toString() {
        return "Staying logged in.";
    }
}

