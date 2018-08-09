package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FireMakingWorker;

public class LightFire extends FireMakingWorker {

    public LightFire(FireMakingMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving())
            return;

        final Item TINDERBOX = Inventory.getFirst(GetTinderBox.TINDERBOX);
        final Item LOGS = Inventory.getFirst(Vars.get().log_type.getName());
        if (TINDERBOX == null || LOGS == null)
            return;

        if (Inventory.use(GetTinderBox.TINDERBOX, LOGS))
            if (!Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving(), 2500)) {
                Log.severe("[STUCK]: Cannot light fire; moving to next lane");
                mission.ignored_tiles.add(Players.getLocal().getPosition());
                mission.is_stuck_in_lane = true;
            }
    }

    @Override
    public String toString() {
        return "Lighting fire";
    }

}

