package sphiinx.script.public_script.spx_aio_walking.tasks;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_aio_walking.data.Vars;

public class WalkToLocation extends Task {

    @Override
    public int execute() {
        Log.info("Walking");
        Movement.walkTo(Vars.get().walk_location);
        return 200;
    }

    @Override
    public boolean validate() {
        return Vars.get().walk_location != null && !Players.getLocal().isMoving() && Vars.get().walk_location.distance() >= 10;
    }

}

