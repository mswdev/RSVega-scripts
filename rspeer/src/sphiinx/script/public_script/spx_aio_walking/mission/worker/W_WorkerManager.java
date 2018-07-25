package sphiinx.script.public_script.spx_aio_walking.mission.worker;

import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerManager;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkMission;
import sphiinx.script.public_script.spx_aio_walking.mission.worker.impl.WalkToLocation;

public class W_WorkerManager extends WorkerManager<WalkMission> {

    public final W_Worker WALK_TO_LOCATION;

    public W_WorkerManager(WalkMission mission) {
        super(mission);
        WALK_TO_LOCATION = new WalkToLocation(mission);
    }

    @Override
    public Worker<WalkMission> decide() {
        if (mission.WALK_POSITION != null && !Players.getLocal().isMoving() && mission.WALK_POSITION.distance() >= 10) {
            return WALK_TO_LOCATION;
        }

        return null;
    }
}

