package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

public class EatFood extends AirOrbChargeWorker {

    public EatFood(AirOrbChargeMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        final Item ITEM = Inventory.getFirst(GetFood.FOOD);
        if (ITEM == null)
            return;

        if (ITEM.click())
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
    }

    @Override
    public String toString() {
        return "Eating food";
    }
}

