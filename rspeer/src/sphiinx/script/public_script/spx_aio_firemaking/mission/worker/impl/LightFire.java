package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FM_Mission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FM_Worker;

public class LightFire extends FM_Worker {

    public LightFire(FM_Mission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving())
            return;

        final Item TINDERBOX = Inventory.getFirst(mission.TINDERBOX);
        final Item LOGS = Inventory.getFirst(Vars.get().log_type.getName());
        if (TINDERBOX == null || LOGS == null)
            return;

        if (Inventory.use(mission.TINDERBOX, LOGS))
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving(), 1500);
    }

    @Override
    public String toString() {
        return "Lighting log";
    }
}

