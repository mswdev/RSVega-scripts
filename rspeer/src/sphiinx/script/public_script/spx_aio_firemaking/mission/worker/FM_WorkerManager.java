package sphiinx.script.public_script.spx_aio_firemaking.mission.worker;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.framework.worker.Worker;
import sphiinx.api.framework.worker.WorkerManager;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FM_Mission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl.*;

public class FM_WorkerManager extends WorkerManager<FM_Mission> {

    private final FM_Worker GET_LOGS;
    private final FM_Worker GET_TINDER_BOX;
    private final FM_Worker WALK_TO_LANE;
    private final FM_Worker LIGHT_FIRE;
    private final FM_Worker UPDATE_LOG;

    public FM_WorkerManager(FM_Mission mission) {
        super(mission);
        GET_LOGS = new GetLogs(mission);
        GET_TINDER_BOX = new GetTinderBox(mission);
        WALK_TO_LANE = new WalkToLane(mission);
        LIGHT_FIRE = new LightFire(mission);
        UPDATE_LOG = new UpdateLog(mission);
    }

    @Override
    public Worker<FM_Mission> decide() {
        if (Vars.get().log_type == null)
            return UPDATE_LOG;

        if (Inventory.getCount(mission.TINDERBOX) <= 0)
            return GET_TINDER_BOX;

        if (Inventory.getCount(Vars.get().log_type.getName()) <= 0)
            return GET_LOGS;

        if (mission.current_lane_position == null || Players.getLocal().getY() != mission.current_lane_position.getY())
            return WALK_TO_LANE;

        return LIGHT_FIRE;
    }
}

