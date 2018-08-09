package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class WalkToLocation extends TIWorker {

    public WalkToLocation(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_end_walk_to_location)
            return false;

        return Vars.get().at_end_walk_position.distance() <= 10;
    }

    @Override
    public void work() {
        Movement.walkTo(Vars.get().at_end_walk_position);
    }

    @Override
    public String toString() {
        return "[END]: Walking to location";
    }
}

