package sphiinx.script.public_script.spx_aio_firemaking.api.script.impl.mission.firemaking_mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.Main;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.impl.mission.firemaking_mission.FireMakingMission;

public class LightFire extends Worker {

    private final FireMakingMission mission;

    public LightFire(FireMakingMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving())
            return;

        final Item tinderbox = Inventory.getFirst(WithdrawTinderBox.TINDERBOX);
        final Item logs = Inventory.getFirst(Main.ARGS.log_type.getName());
        if (tinderbox == null || logs == null)
            return;

        if (Inventory.use(WithdrawTinderBox.TINDERBOX, logs))
            if (!Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving(), 2500)) {
                Log.severe("[STUCK]: Cannot light fire; moving to next lane");
                mission.getIgnoredTiles().add(Players.getLocal().getPosition());
                mission.setIsStuckInLane(true);
            }
    }

    @Override
    public String toString() {
        return "Lighting fire.";
    }

}

