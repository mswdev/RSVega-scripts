package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import sphiinx.api.script.framework.worker.Worker;

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

