package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.api.script.framework.worker.Worker;

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

